import { Component } from '@angular/core';

import { Platform, NavController, Events } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { Pages } from './interfaces/pages';
import { IUser } from './pages/register/model/usuario.model';
import { Storage } from '@ionic/storage';
import { CONSTANTES } from './core/config/constantes';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  user: IUser = new IUser();
  public appPages: Array<Pages>;
  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    public navCtrl: NavController,
    private events: Events,
    private storage: Storage
  ) {
    this.initializeApp();
  }

 async initializeApp() {
   this.user = new IUser();
    this.events.subscribe('menu', values => {
      this.appPages = values;
      this.storage.get(CONSTANTES.USER_CACHE).then(value => {
        this.user = value;
      });
    });

    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    }).catch(() => {});
  }

  goToEditProgile() {
    this.navCtrl.navigateForward('edit-profile');
  }

  logout() {
    this.storage.clear();
    this.navCtrl.navigateRoot('/');
  }
}
