import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { NavController, MenuController, LoadingController } from '@ionic/angular';
import { Location } from '@angular/common';
import { NotaService } from '../service/nota.service';
import { AsyncSubject, Subject } from 'rxjs';
import { INotification } from 'src/app/core/config/notifications/notification';
import { INota } from '../model/nota.model';
import { takeUntil } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastController, AlertController } from '@ionic/angular';
import { CursosService } from '../../curso/service/cursos.service';
import { AvaliacoesService } from '../../avaliacoes/service/avaliacoes.service';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { CrudService } from 'src/app/shared/services/crud.service';


@Component({
    selector: 'app-nota-edit',
    templateUrl: './nota-edit.component.html',
    styleUrls: ['./nota-edit.component.scss']
})
export class NotaEditComponent implements OnInit, OnDestroy {

    listCursos: any;
    listAvaliacoes: any;
    listMatriculas: [];
    nota: INota = new INota();
    pageType: string;
    form: FormGroup;
    formErrors: any;
    toast: any;
    alert: any;

    private urnEndpoint = `/${Constantes.CURSOS_URL}`;

    private notificationSuccess$: AsyncSubject<INotification>;
    private notificationError$: AsyncSubject<INotification>;

    // Private
    private _unsubscribeAll: Subject<any>;

    constructor(
        public navCtrl: NavController,
        public menuCtrl: MenuController,
        private _location: Location,
        private _router: Router,
        public loadingCtrl: LoadingController,
        public toastController: ToastController,
        private _alertController: AlertController,
        private _formBuilder: FormBuilder,
        private _service: NotaService,
        private _serviceCursos: CursosService,
        private _serviceAvaliacoes: AvaliacoesService,
        private _serviceCrud: CrudService
    ) {

        this.notificationSuccess$ = this._service.subjectSuccess;
        this.notificationError$ = this._service.subjectError;

        this.form = this.createForm();
        // Set the private defaults
        this._unsubscribeAll = new Subject();

        this.formErrors = {
            nota: {},
            idNota: {},
            idCurso: {},
            idAvaliacao: {},
            idMatricula: {}
        };


    }

    ionViewWillEnter() {
        this.menuCtrl.enable(false);
    }


    /**
     * On init
     */
    ngOnInit(): void {
        // Subscribe to update object on changes
        this._service.onObjectChanged
            .pipe(takeUntil(this._unsubscribeAll))
            .subscribe(obj => {

                if (obj) {
                    this.nota = new INota(obj);
                    this.pageType = 'edit';
                    this.objToForm(obj);
                } else {
                    this.pageType = 'new';
                    this.nota = new INota();
                }

                this.form.valueChanges
                    .pipe(takeUntil(this._unsubscribeAll))
                    .subscribe(() => {
                        this.onFormValuesChanged();
                    });

            });

            this._serviceCursos.getAll()
            .then(dados => {
                if ( dados ) {
                    this.listCursos = dados;
                }
            });

            this._serviceAvaliacoes.getAll()
            .then(dados => {
                if ( dados ) {
                    this.listAvaliacoes = dados;
                }
            });
    }

    /**
     * On destroy
     */
    ngOnDestroy(): void {
        // Unsubscribe from all subscriptions
        this._unsubscribeAll.next();
        this._unsubscribeAll.complete();

    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Create product form
     *
     * @returns {FormGroup}
     */
    createForm(): FormGroup {
        return this._formBuilder.group({
            idNota: [this.nota.idNota],
            idAvaliacao: [this.nota.avaliacao.idAvaliacao, Validators.required],
            idCurso: [this.nota.curso.idCurso, Validators.required],
            nota: [this.nota.nota, Validators.required],
            idMatricula: [this.nota.aluno.matricula.idMatricula]
        });
    }

    /**
     * On form values changed
     */
    onFormValuesChanged(): void {
        for (const field in this.form) {
            if (!this.formErrors.hasOwnProperty(field)) {
                continue;
            }

            // Clear previous errors
            this.formErrors[field] = {};

            // Get the control
            const control = this.form.get(field);

            if (control && control.dirty && !control.valid) {
                this.formErrors[field] = control.errors;
            }
        }
    }

    /**
     * Save product
     */
    save(): void {
        const data = this.form.getRawValue();
        this.formToInterface();

        this._service.save(this.nota)
            .then((response) => {

                // Trigger the subscription with new data
                this._service.onObjectChanged.next(response);
                this.showToast('Atualizado com sucesso!');

            }).catch((err) => {
                this.showToast(err.error.message);
            });
    }

    /**
     * Add product
     */
    add(): void {
        const data = this.form.getRawValue();
        if (this.form.valid) {
            this.formToInterface();

            this._service.add(this.nota)
                .then((response) => {

                    // Trigger the subscription with new data
                    this._service.onObjectChanged.next(response.data);

                    this.showToast('Inserido com sucesso!');

                    // Change the location with new one
                    this._location.go(Constantes.NOTAS_URL);
                    // this.navCtrl.navigateRoot(`/${Constantes.NOTAS_URL}`);
                }).catch((err) => {
                    this.showToast(err.error.message);
                });
        }
    }

    /**
     * Delete product
     */
    delete(): void {
        const data = this.form.getRawValue();
        this.formToInterface();

        this._service.delete(this.nota)
            .then((response) => {

                // Change the location with new one
                this._location.go(Constantes.NOTAS_URL);
                this._router.navigate([Constantes.NOTAS_URL]);
            }).catch((err) => {
                this.showToast(err.error.message);
            });
    }

    public loadMatriculas(matricula: any) {
        this._serviceCrud.get(`${ENDPOINT_API}/${Constantes.MATRICULAS_URL}/${Constantes.CURSOS_URL}`, matricula.detail.value)
        .then(dados => {
            if ( dados ) {
                this.listMatriculas = dados;
            }
        });
    }

    public deleteAlert(): void {
        this.presentAlertConfirm();
    }

    private formToInterface(): void {
        this.nota.nota = this.form.value.nota;
        this.nota.curso.idCurso = this.form.value.idCurso;
        this.nota.avaliacao.idAvaliacao = this.form.value.idAvaliacao;
        this.nota.aluno.matricula.idMatricula = this.form.value.idMatricula;
    }

    private objToForm(object: any): void {
        for (const field in this.form.value) {
            if (!this.form.hasOwnProperty(field)) {
                this.form.controls[field].setValue(object[field]);
                continue;
            }
        }
    }

    private showToast(msg) {
        this.toast = this.toastController.create({
            message: msg,
            duration: 2000
        }).then((toastData) => {
            console.log(toastData);
            toastData.present();
        });
    }

    private presentAlertConfirm() {
        this.alert = this._alertController.create({
            header: 'Mensagem',
            message: 'Confirmar <strong>exclusão</strong>?',
            translucent: false,
            buttons: [
                {
                    text: 'Cancelar',
                    role: 'cancel',
                    cssClass: 'secondary',
                    handler: (blah) => {
                        console.log('Confirm Cancel: blah');
                    }
                }, {
                    text: 'Confirmar',
                    handler: () => {
                        this.delete();
                        console.log('Confirm Okay');
                    }
                }
            ]
        }).then((alertData) => {
            alertData.present();
        });
    }

    private HideToast() {
        this.toast = this.toastController.dismiss();
    }

}
