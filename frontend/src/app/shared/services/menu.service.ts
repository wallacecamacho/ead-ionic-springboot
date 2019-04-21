import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, AsyncSubject } from 'rxjs';
import { Pages } from 'src/app/interfaces/pages';
import { Events } from '@ionic/angular';

@Injectable()
export class MenuService {
    public appPages: Array<Pages>;
    public appPagesAdmin: Array<Pages>;
    public appPagesAluno: Array<Pages>;

    /**
     * Constructor
     *
     * @param {HttpClient} _httpClient
     */
    constructor(
        private _httpClient: HttpClient,
        private events: Events
    ) {

        this.appPages = [
            {
                title: 'Home',
                url: '/home-results',
                direct: 'root',
                icon: 'home',
                perfil: 'ALUNO'
            },
            {
                title: 'Configurações',
                url: '/settings',
                direct: 'forward',
                icon: 'cog',
                perfil: 'ALUNO'
            },
            {
                title: 'Cursos',
                url: '/cursos',
                direct: 'forward',
                icon: 'cog',
                perfil: 'ADMIN'
            },
            {
                title: 'Avaliação',
                url: '/avaliacoes',
                direct: 'forward',
                icon: 'cog',
                perfil: 'ADMIN'
            },
            {
                title: 'Notas',
                url: '/notas',
                direct: 'forward',
                icon: 'cog',
                perfil: 'ADMIN'
            },
            {
                title: 'Sobre',
                url: '/about',
                direct: 'forward',
                icon: 'information-circle-outline',
                perfil: 'ALUNO'
            },
        ];

        this.appPagesAluno = [
            {
                title: 'Home',
                url: '/home-results',
                direct: 'root',
                icon: 'home',
                perfil: 'ALUNO'
            },
            {
                title: 'Sobre',
                url: '/about',
                direct: 'forward',
                icon: 'information-circle-outline',
                perfil: 'ALUNO'
            },
        ];

        this.appPagesAdmin = [
            {
                title: 'Cursos',
                url: '/cursos',
                direct: 'forward',
                icon: 'cog',
                perfil: 'ADMIN'
            },
            {
                title: 'Avaliação',
                url: '/avaliacoes',
                direct: 'forward',
                icon: 'cog',
                perfil: 'ADMIN'
            },
            {
                title: 'Notas',
                url: '/notas',
                direct: 'forward',
                icon: 'cog',
                perfil: 'ADMIN'
            },
        ];
    }

    loadMenu(values: Array<Pages>) {
        this.events.publish('menu', values);
    }
}
