import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { NavController, MenuController, LoadingController } from '@ionic/angular';
import { Location } from '@angular/common';
import { AvaliacaoService } from '../service/avaliacao.service';
import { AsyncSubject, Subject } from 'rxjs';
import { INotification } from 'src/app/core/config/notifications/notification';
import { IAvaliacao } from '../model/avaliacao.model';
import { takeUntil } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastController, AlertController } from '@ionic/angular';
import { CursosService } from '../../curso/service/cursos.service';


@Component({
    selector: 'app-avaliacao-edit',
    templateUrl: './avaliacao-edit.component.html',
    styleUrls: ['./avaliacao-edit.component.scss']
})
export class AvaliacaoEditComponent implements OnInit, OnDestroy {

    public listCursos: any;
    avaliacao: IAvaliacao = new IAvaliacao();
    pageType: string;
    form: FormGroup;
    formErrors: any;
    toast: any;
    alert: any;

    cursoObject: any;
    chosenCurso: any;

    private urnEndpoint = `/${Constantes.AVALIACOES_URL}`;

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
        private _service: AvaliacaoService,
        private _serviceCursos: CursosService
    ) {

        this.notificationSuccess$ = this._service.subjectSuccess;
        this.notificationError$ = this._service.subjectError;

        this.form = this.createForm();
        // Set the private defaults
        this._unsubscribeAll = new Subject();

        this.formErrors = {
            nomeAvaliacao: {},
            dataAvaliacao: {},
            idCurso: {}
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
                    this.avaliacao = new IAvaliacao(obj);
                    this.pageType = 'edit';
                    // this.objToForm(obj);
                    this.form = this.createForm();
                } else {
                    this.avaliacao = new IAvaliacao();
                    this.pageType = 'new';
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
            nomeAvaliacao: [this.avaliacao.nomeAvaliacao, Validators.required],
            dataAvaliacao: [this.avaliacao.dataAvaliacao, Validators.required],
            idCurso: [this.avaliacao.curso.idCurso, Validators.required],
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

    public compareWithFn(o1, o2): boolean {
        return o1 && o2 ? o1.id === o2.id : o1 === o2;
    }

    public changedCurso(obj): void {
        this.cursoObject = this.listCursos[this.chosenCurso];
    }

    /**
     * Save product
     */
    save(): void {
        const data = this.form.getRawValue();
        this.formToInterface();

        this._service.save(this.avaliacao)
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

            this._service.add(this.avaliacao)
                .then((response) => {

                    // Trigger the subscription with new data
                    this._service.onObjectChanged.next(response.data);

                    this.showToast('Inserido com sucesso!');

                    // Change the location with new one
                     this._location.go(Constantes.AVALIACOES_URL);
                    // this.navCtrl.navigateRoot(`/${Constantes.AVALIACOES_URL}`);
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

        this._service.delete(this.avaliacao)
            .then((response) => {

                // Change the location with new one
                this._location.go(Constantes.AVALIACOES_URL);
                this._router.navigate([Constantes.AVALIACOES_URL]);
            }).catch((err) => {
                this.showToast(err.error.message);
            });
    }

    deleteAlert(): void {
        this.presentAlertConfirm();
    }

    private formToInterface(): void {
        this.avaliacao.nomeAvaliacao = this.form.value.nomeAvaliacao;
        this.avaliacao.dataAvaliacao = this.form.value.dataAvaliacao;
        this.avaliacao.curso.idCurso = this.form.value.idCurso;
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
