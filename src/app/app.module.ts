//angular
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

//primeng
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { CheckboxModule } from 'primeng/checkbox';
import { DividerModule } from 'primeng/divider';
import { DropdownModule } from 'primeng/dropdown';
import { FieldsetModule } from 'primeng/fieldset';
import { InputTextModule } from 'primeng/inputtext';
import { MenuModule } from 'primeng/menu';
import { MenubarModule } from 'primeng/menubar';
import { PanelModule } from 'primeng/panel';
import { PasswordModule } from 'primeng/password';
import { RadioButtonModule } from 'primeng/radiobutton';
import { SidebarModule } from 'primeng/sidebar';
import { TableModule } from 'primeng/table';
import { TabMenuModule } from 'primeng/tabmenu';
import { TabViewModule } from 'primeng/tabview';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { DataViewModule } from 'primeng/dataview';

//servicios
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AlbumService } from './services/data/album.service';
import { ArtistaService } from './services/data/artista.service';
import { CancionService } from './services/data/cancion.service';
import { GeneroService } from './services/data/genero.service';
import { ListaService } from './services/data/lista.service';
import { RolService } from './services/data/rol.service';
import { UsuarioService } from './services/data/usuario.service';
import { SesionService } from './services/sesion.service';

//componentes
import { AppComponent } from './app.component';
import { BodyComponent } from './menu/body/body.component';
import { NavMenuComponent } from './menu/nav-menu/nav-menu.component';
import { LoginComponent } from './modules/Login/Login.component';
import { AjustesComponent } from './modules/ajustes/ajustes.component';
import { BibliotecaComponent } from './modules/biblioteca/biblioteca.component';
import { BannerComponent } from './modules/buscar/banner/banner.component';
import { BuscarComponent } from './modules/buscar/buscar.component';
import { HomeComponent } from './modules/home/home.component';
import { CardComponent } from './shared/card/card.component';
import { ResultadosComponent } from './shared/resultados/resultados.component';
import { TituloComponent } from './shared/titulo/titulo.component';
import { ListaComponent } from './modules/lista/lista.component';
import { CancionesTablaComponent } from './shared/canciones-tabla/canciones-tabla.component';
import { BannerListasComponent } from './shared/banner-listas/banner-listas.component';
import { AlbumComponent } from './modules/album/album.component';
import { ArtistaComponent } from './modules/artista/artista.component';

const appRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'buscar', component: BuscarComponent },
  { path: 'login', component: LoginComponent },
  { path: 'biblioteca', component: BibliotecaComponent },
  { path: 'ajustes', component: AjustesComponent },
  { path: 'lista/:id', component: ListaComponent },
  { path: 'album/:id', component: AlbumComponent },
  { path: 'artista/:id', component: ArtistaComponent },
  // { path: 'biblioteca', component: BibliotecaComponent,
  // children: [
  //   { path: 'listas', component: ListasComponent },
  //   { path: 'artistas', component: ArtistasComponent },
  //   { path: 'albumes', component: AlbumesComponent },
  // ]},
];

@NgModule({
  declarations: [
    AppComponent,
    NavMenuComponent,
    HomeComponent,
    BodyComponent,
    LoginComponent,
    BibliotecaComponent,
    CardComponent,
    BuscarComponent,
    BannerComponent,
    ResultadosComponent,
    TituloComponent,
    AjustesComponent,
    ListaComponent,
    CancionesTablaComponent,
    BannerListasComponent,
    AlbumComponent,
    ArtistaComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    TableModule,
    ButtonModule,
    SidebarModule,
    BrowserAnimationsModule,
    MenuModule,
    ToastModule,
    MenubarModule,
    TabMenuModule,
    TabViewModule,
    CardModule,
    InputTextModule,
    ToolbarModule,
    DividerModule,
    FieldsetModule,
    CheckboxModule,
    RadioButtonModule,
    PanelModule,
    ReactiveFormsModule,
    PasswordModule,
    DropdownModule,
    HttpClientModule,
    DataViewModule,
  ],
  providers: [
    HttpClient,
    MessageService,
    CookieService,
    SesionService,
    ArtistaService,
    AlbumService,
    CancionService,
    UsuarioService,
    GeneroService,
    ListaService,
    RolService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
