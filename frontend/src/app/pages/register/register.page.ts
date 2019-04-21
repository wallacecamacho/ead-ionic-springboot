import { Component, OnInit } from '@angular/core';
import { Storage } from '@ionic/storage';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavController, MenuController, LoadingController, ToastController } from '@ionic/angular';
import { AsyncSubject, Subject } from 'rxjs';
import { INotification } from 'src/app/core/config/notifications/notification';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { IUser } from './model/usuario.model';
import { UsuarioService } from './service/usuario.service';
import { CrudService } from 'src/app/shared/services/crud.service';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { CONSTANTES } from 'src/app/core/config/constantes';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  public onRegisterForm: FormGroup;

  private notificationSuccess$: AsyncSubject<INotification>;
  private notificationError$: AsyncSubject<INotification>;

  // Private
  private _unsubscribeAll: Subject<any>;

  private urnEndpoint = `/${Constantes.USUARIOS_URL}`;
  private toast: any;
  private user: IUser = new IUser();
  listCursos: any;

  constructor(
    public navCtrl: NavController,
    public menuCtrl: MenuController,
    public loadingCtrl: LoadingController,
    private formBuilder: FormBuilder,
    private _service: UsuarioService,
    public toastController: ToastController,
    private _serviceCrud: CrudService,
    private _storage: Storage,
  ) { }

  ionViewWillEnter() {
    this.menuCtrl.enable(false);
  }

  ngOnInit() {
    this.onRegisterForm = this.formBuilder.group({
      'fullName': [null, Validators.compose([
        Validators.required
      ])],
      'email': [null, Validators.compose([Validators.required, Validators.email])],
      'password': [null, Validators.compose([
        Validators.required
      ])],
      'cpf': [null, Validators.compose([
        Validators.required
      ])],
      'estado': [null, Validators.compose([])],
      'municipio': [null, Validators.compose([])],
      'endereco': [null, Validators.compose([])],
      'telefone': [null, Validators.compose([])],
      'idCurso': [null, Validators.compose([
        Validators.required
      ])],
    });

    this._serviceCrud.getAll(`${ENDPOINT_API}/${Constantes.CURSOS_URL}`)
    .then(dados => {
        if ( dados ) {
            this.listCursos = dados;
        }
    });

  }

  async signUp() {
    const data = this.onRegisterForm.getRawValue();
    this.formToInterface();
    this.user.perfil = 'ALUNO';

      this._service.add(this.user)
      .then((response) => {
        this.showToast('Cadastrado com sucesso!');
        this._storage.set(CONSTANTES.USER_CACHE, this.user);
        this.navCtrl.navigateRoot('/register');

    }).catch((err) => {
      this.showToast(err.error.message);
    });

    // const loader = await this.loadingCtrl.create({
     //  duration: 2000
    // });
    // loader.present();
    // loader.onWillDismiss().then(() => {

   // });
  }

  // // //
  goToLogin() {
    this.navCtrl.navigateRoot('/');
  }

  private formToInterface(): void {
    this.user.aluno.nome = this.onRegisterForm.value.fullName;
    this.user.idUsuario = this.onRegisterForm.value.idUsuario;
    this.user.email = this.onRegisterForm.value.email;
    this.user.perfil = this.onRegisterForm.value.perfil;
    this.user.senha = this.onRegisterForm.value.password;
    this.user.aluno.municipio = this.onRegisterForm.value.municipio;
    this.user.aluno.estado = this.onRegisterForm.value.estado;
    this.user.aluno.endereco = this.onRegisterForm.value.endereco;
    this.user.aluno.telefone = this.onRegisterForm.value.telefone;
    this.user.aluno.cpf = this.onRegisterForm.value.cpf;
    this.user.aluno.curso.idCurso = this.onRegisterForm.value.idCurso;
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
}
