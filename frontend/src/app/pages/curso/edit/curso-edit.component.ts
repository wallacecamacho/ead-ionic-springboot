import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { NavController, MenuController, LoadingController } from '@ionic/angular';
import { Location } from '@angular/common';
import { CursoService } from '../service/curso.service';
import { AsyncSubject, Subject } from 'rxjs';
import { INotification } from 'src/app/core/config/notifications/notification';
import { ICurso } from '../model/curso.model';
import { takeUntil } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastController, AlertController } from '@ionic/angular';


@Component({
    selector: 'app-curso-edit',
    templateUrl: './curso-edit.component.html',
    styleUrls: ['./curso-edit.component.scss']
})
export class CursoEditComponent implements OnInit, OnDestroy {

    curso: ICurso = {} as ICurso;
    pageType: string;
    form: FormGroup;
    formErrors: any;
    toast: any;
    alert: any;

    public listCursos: any;
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
        private _service: CursoService
    ) {

        this.notificationSuccess$ = this._service.subjectSuccess;
        this.notificationError$ = this._service.subjectError;

        this.form = this.createForm();
        // Set the private defaults
        this._unsubscribeAll = new Subject();

        this.formErrors = {
            nome: {},
            codigoCurso: {},
            anoSemestre: {}
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
            .subscribe(pCurso => {

                if (pCurso) {
                    this.curso = new ICurso(pCurso);
                    this.pageType = 'edit';
                    this.objToForm(pCurso);
                } else {
                    this.pageType = 'new';
                    this.curso = new ICurso();
                }

                this.form.valueChanges
                    .pipe(takeUntil(this._unsubscribeAll))
                    .subscribe(() => {
                        this.onFormValuesChanged();
                    });

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
            nome: [this.curso.nome, Validators.required],
            codigoCurso: [this.curso.codigoCurso, Validators.required],
            anoSemestre: [this.curso.anoSemestre, Validators.required],
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

        this._service.save(this.curso)
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

            this._service.add(this.curso)
                .then((response) => {

                    // Trigger the subscription with new data
                    this._service.onObjectChanged.next(response.data);

                    this.showToast('Inserido com sucesso!');

                    // Change the location with new one
                    this._location.go(Constantes.CURSOS_URL + '/' + this.curso.idCurso + '/' + this.curso.nome);
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

        this._service.delete(this.curso)
            .then((response) => {

                // Change the location with new one
                this._location.go(Constantes.CURSOS_URL);
                this._router.navigate([Constantes.CURSOS_URL]);
            }).catch((err) => {
                this.showToast(err.error.message);
            });
    }

    deleteAlert(): void {
        this.presentAlertConfirm();
    }

    private formToInterface(): void {
        this.curso.nome = this.form.value.nome;
        this.curso.codigoCurso = this.form.value.codigoCurso;
        this.curso.anoSemestre = this.form.value.anoSemestre;
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
