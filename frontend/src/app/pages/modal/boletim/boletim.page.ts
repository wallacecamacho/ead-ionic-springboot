import { Component, Input, OnInit } from '@angular/core';
import { NavController, ModalController } from '@ionic/angular';
import { DomSanitizer } from '@angular/platform-browser';
import { CrudService } from 'src/app/shared/services/crud.service';
import { ENDPOINT_API } from 'src/app/core/api.config';
import { Constantes } from 'src/app/core/config/constantes/constantes';
import { IUser } from '../../register/model/usuario.model';
import { Storage } from '@ionic/storage';
import { CONSTANTES } from 'src/app/core/config/constantes';

@Component({
  selector: 'app-boletim',
  templateUrl: './boletim.page.html',
  styleUrls: ['./boletim.page.scss'],
})
export class BoletimPage implements OnInit {
  @Input() value: any;
  public listBoletim: any = [];
  private user: IUser = new IUser();
  public notaTotal = 0;
  public situacao: string;

  constructor(
    private nav: NavController,
    private modalCtrl: ModalController,
    private sanitizer: DomSanitizer,
    private _serviceCrud: CrudService,
    private _storage: Storage,
  ) { }

  async ngOnInit() {
    await this._storage.get(CONSTANTES.USER_CACHE).then(value => {
      this.user = value;
    });
    await this._serviceCrud.get(`${ENDPOINT_API}/${Constantes.NOTAS_URL}/${Constantes.ALUNOS_URL}`, this.user.aluno.idAluno)
      .then(async dados => {
        if (dados) {
          this.listBoletim = dados;
        }
      });

    this.notaTotal = await this.listBoletim.map(valorItem => valorItem.nota)
      .reduce((result, curr) => {
        return result.nota + curr;
      });

    this.notaTotal = this.notaTotal / this.listBoletim.length;
    this.situacao = this.notaTotal >= 6 ? 'aprovado' : 'reprovado';


  }

  closeModal() {
    this.modalCtrl.dismiss();
  }

}
