import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavController, MenuController, ToastController, AlertController, LoadingController } from '@ionic/angular';
import { CrudService } from 'src/app/shared/services/crud.service';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { Storage } from '@ionic/storage';
import { IUser } from '../register/model/usuario.model';
import { MenuService } from 'src/app/shared/services/menu.service';
import { AsyncSubject, BehaviorSubject } from 'rxjs';
import { CONSTANTES } from 'src/app/core/config/constantes';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  public onLoginForm: FormGroup;

  user: IUser = new IUser();

  public notificationSuccess$ = new AsyncSubject<any>();
  public notification$ = new BehaviorSubject<any>({});

  alert: any;

  constructor(
    public navCtrl: NavController,
    public menuCtrl: MenuController,
    public toastCtrl: ToastController,
    public alertCtrl: AlertController,
    public loadingCtrl: LoadingController,
    private formBuilder: FormBuilder,
    private _serviceCrud: CrudService,
    private _storage: Storage,
    private _menuService: MenuService,
    private _alertController: AlertController,
  ) {
  }

  ionViewWillEnter() {
    this.menuCtrl.enable(false);
  }

  ngOnInit() {

    this.onLoginForm = this.formBuilder.group({
      'email': [null, Validators.compose([
        Validators.required, Validators.email
      ])],
      'senha': [null, Validators.compose([Validators.required])]
    });
  }

  async forgotPass() {
    const alert = await this.alertCtrl.create({
      header: 'Forgot Password?',
      message: 'Enter you email address to send a reset link password.',
      inputs: [
        {
          name: 'email',
          type: 'email',
          placeholder: 'Email'
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          cssClass: 'secondary',
          handler: () => {
            console.log('Confirm Cancel');
          }
        }, {
          text: 'Confirm',
          handler: async () => {
            const loader = await this.loadingCtrl.create({
              duration: 2000
            });

            loader.present();
            loader.onWillDismiss().then(async l => {
              const toast = await this.toastCtrl.create({
                showCloseButton: true,
                message: 'Email was sended successfully.',
                duration: 3000,
                position: 'bottom'
              });

              toast.present();
            });
          }
        }
      ]
    });

    await alert.present();
  }

  // // //
  goToRegister() {
    this.navCtrl.navigateRoot('/register');
  }

  goToHome() {

    this._serviceCrud.add(`${ENDPOINT_API}/${Constantes.LOGIN_URL}`, this.onLoginForm.getRawValue())
      .then(async dados => {
        if (dados && dados.senha === this.onLoginForm.value.senha) {
         await this._storage.set(CONSTANTES.USER_CACHE, dados);
          this.user = dados;
          if (this.user.perfil === 'ALUNO') {
            this._menuService.loadMenu(this._menuService.appPagesAluno);
          } else if (this.user.perfil === 'ADMIN') {
            this._menuService.loadMenu(this._menuService.appPagesAdmin);
          }
          this.navCtrl.navigateRoot('/home-results');
        } else {
          this.presentAlertConfirm();
        }
      });


  }

  private presentAlertConfirm() {
    this.alert = this._alertController.create({
        header: 'Mensagem',
        message: 'Verifique<strong> seu usuário ou senha!</strong>',
        translucent: false,
        buttons: [
            {
                text: 'Ok',
                role: 'Ok',
                cssClass: 'secondary',
                handler: (blah) => {
                }
            }
        ]
    }).then((alertData) => {
        alertData.present();
    });
}

}
