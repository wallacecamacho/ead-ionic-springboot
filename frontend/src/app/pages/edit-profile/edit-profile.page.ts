import { Component, OnInit } from '@angular/core';
import { NavController, LoadingController, ToastController, MenuController } from '@ionic/angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AsyncSubject, Subject } from 'rxjs';
import { INotification } from 'src/app/core/config/notifications/notification';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { UsuarioService } from '../register/service/usuario.service';
import { IUser } from '../register/model/usuario.model';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.page.html',
  styleUrls: ['./edit-profile.page.scss'],
})
export class EditProfilePage implements OnInit {
  public onRegisterForm: FormGroup;

  private notificationSuccess$: AsyncSubject<INotification>;
  private notificationError$: AsyncSubject<INotification>;

  // Private
  private _unsubscribeAll: Subject<any>;

  private urnEndpoint = `/${Constantes.USUARIOS_URL}`;
  private toast: any;
  private user: IUser = new IUser();

  constructor(
    public navCtrl: NavController,
    public menuCtrl: MenuController,
    public loadingCtrl: LoadingController,
    private formBuilder: FormBuilder,
    private _service: UsuarioService,
    public toastController: ToastController,
    private _storage: Storage,
  ) { }

  async sendData() {
    const loader = await this.loadingCtrl.create({
      duration: 2000
    });

    loader.present();
    loader.onWillDismiss().then(async l => {
      const toast = await this.toastController.create({
        showCloseButton: true,
        cssClass: 'bg-profile',
        message: 'Seus dados foram salvos!',
        duration: 3000,
        position: 'bottom'
      });

      toast.present();
      this.navCtrl.navigateForward('/home-results');
    });
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
      'telefone': [null, Validators.compose([])]
    });
  }
}
