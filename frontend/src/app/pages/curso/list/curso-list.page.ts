import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuController, LoadingController, NavParams, NavController } from '@ionic/angular';
import { CursosService } from '../service/cursos.service';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { Constantes } from 'src/app/core/config/constantes/constantes';


@Component({
  selector: 'app-curso',
  templateUrl: './curso-list.page.html',
  styleUrls: ['./curso-list.page.scss'],
})
export class CursoListPageComponent implements OnInit, OnDestroy {

  public onRegisterForm: FormGroup;

  public listCursos: any;
  private urnEndpoint = `/${Constantes.CURSOS_URL}`;


  constructor(
    public navCtrl: NavController,
    public menuCtrl: MenuController,
    public loadingCtrl: LoadingController,
    private _service: CursosService
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
                    this.listCursos = dados;
                }
            });
    }

    /**
     * On destroy
     */
    ngOnDestroy(): void {

    }

    novoCurso() {
      this.navCtrl.navigateForward(`${this.urnEndpoint}/edit/new`);
    }

    editCurso(value: any) {
      this.navCtrl.navigateForward(`${this.urnEndpoint}/edit/${value}`);
    }

}
