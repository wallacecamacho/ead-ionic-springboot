import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuController, LoadingController, NavParams, NavController } from '@ionic/angular';
import { AvaliacoesService } from '../service/avaliacoes.service';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { Constantes } from 'src/app/core/config/constantes/constantes';


@Component({
  selector: 'app-avaliacao',
  templateUrl: './avaliacao-list.page.html',
  styleUrls: ['./avaliacao-list.page.scss'],
})
export class AvaliacaoListPageComponent implements OnInit, OnDestroy {

  public onRegisterForm: FormGroup;

  public listAvaliacoes: any;
  private urnEndpoint = `/${Constantes.AVALIACOES_URL}`;


  constructor(
    public navCtrl: NavController,
    public menuCtrl: MenuController,
    public loadingCtrl: LoadingController,
    private _service: AvaliacoesService
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
                    this.listAvaliacoes = dados;
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
