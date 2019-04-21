import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuController, LoadingController, NavParams, NavController } from '@ionic/angular';
import { NotasService } from '../service/notas.service';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { Constantes } from 'src/app/core/config/constantes/constantes';


@Component({
  selector: 'app-nota',
  templateUrl: './nota-list.page.html',
  styleUrls: ['./nota-list.page.scss'],
})
export class NotaListPageComponent implements OnInit, OnDestroy {

  public onRegisterForm: FormGroup;

  public listNotas: any;
  private urnEndpoint = `/${Constantes.NOTAS_URL}`;


  constructor(
    public navCtrl: NavController,
    public menuCtrl: MenuController,
    public loadingCtrl: LoadingController,
    private _service: NotasService
  ) { }

  ionViewWillEnter() {
    this.menuCtrl.enable(false);
  }

    /**
     * On init
     */
    ngOnInit(): void {
        // Subscribe to update object on changes
        this._service.onObjectsChanged
            .subscribe(dados => {
                if ( dados ) {
                    this.listNotas = dados;
                }
            });
    }

    /**
     * On destroy
     */
    ngOnDestroy(): void {

    }

    novo() {
      this.navCtrl.navigateForward(`${this.urnEndpoint}/edit/new`);
    }

    edit(value: any) {
      this.navCtrl.navigateForward(`${this.urnEndpoint}/edit/${value}`);
    }

}
