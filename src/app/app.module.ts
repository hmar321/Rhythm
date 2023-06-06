//angular
import { NgModule, inject } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

//primeng
import { ConfirmationService, MessageService } from 'primeng/api';
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
import { DialogService, DynamicDialogModule } from 'primeng/dynamicdialog';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MultiSelectModule } from 'primeng/multiselect';
import { AutoFocusModule } from 'primeng/autofocus';

//servicios
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AlbumService } from './services/data/album.service';
import { ArtistaService } from './services/data/artista.service';
import { CancionService } from './services/data/cancion.service';
import { GeneroService } from './services/data/genero.service';
import { ListaService } from './services/data/lista.service';
import { RolService } from './services/data/rol.service';
import { UsuarioService } from './services/data/usuario.service';
import { SesionService } from './services/util/sesion.service';
import { LoginGuardian } from './modules/Login/login-guardian';
import { FavoritoService } from './services/util/favorito.service';

//componentes
import { AppComponent } from './app.component';
import { BodyComponent } from './shared/body/body.component';
import { NavMenuComponent } from './shared/nav-menu/nav-menu.component';
import { LoginComponent } from './modules/Login/Login.component';
import { BibliotecaComponent } from './modules/biblioteca/biblioteca.component';
import { BuscarComponent } from './modules/buscar/buscar.component';
import { HomeComponent } from './modules/home/home.component';
import { CardComponent } from './shared/card/card.component';
import { ResultadosComponent } from './shared/resultados/resultados.component';
import { TituloComponent } from './shared/titulo/titulo.component';
import { ListaComponent } from './shared/lista/lista.component';
import { CancionesTablaComponent } from './shared/canciones-tabla/canciones-tabla.component';
import { BannerListasComponent } from './shared/banner-listas/banner-listas.component';
import { AlbumComponent } from './shared/album/album.component';
import { ArtistaComponent } from './shared/artista/artista.component';
import { RegistroComponent } from './modules/Login/registro/registro.component';
import { AgregarCancionComponent } from './shared/lista/agregar-cancion/agregar-cancion.component';
import { CancionComponent } from './shared/cancion/cancion.component';
import { GeneroComponent } from './shared/genero/genero.component';
import { CrearListaComponent } from './modules/biblioteca/crear-lista/crear-lista.component';
import { PerfilComponent } from './modules/perfil/perfil.component';


const appRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'home/genero', component: GeneroComponent },
  { path: 'buscar', component: BuscarComponent },
  { path: 'buscar/lista', component: ListaComponent },
  { path: 'buscar/artista', component: ArtistaComponent },
  { path: 'buscar/artista/album', component: AlbumComponent },
  { path: 'buscar/album', component: AlbumComponent },
  { path: 'buscar/cancion', component: CancionComponent },
  { path: 'biblioteca', component: BibliotecaComponent, canActivate: [() => inject(LoginGuardian).canActivate()], },
  { path: 'biblioteca/lista', component: ListaComponent, canActivate: [() => inject(LoginGuardian).canActivate()], },
  { path: 'biblioteca/artista', component: ArtistaComponent, canActivate: [() => inject(LoginGuardian).canActivate()], },
  { path: 'biblioteca/artista/album', component: AlbumComponent, canActivate: [() => inject(LoginGuardian).canActivate()], },
  { path: 'biblioteca/album', component: AlbumComponent, canActivate: [() => inject(LoginGuardian).canActivate()], },
  { path: 'favoritos', component: ListaComponent, canActivate: [() => inject(LoginGuardian).canActivate()], },
  { path: 'perfil', component: PerfilComponent, canActivate: [() => inject(LoginGuardian).canActivate()], },
  { path: 'login', component: LoginComponent },
  { path: 'login/registro', component: RegistroComponent },
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
    ResultadosComponent,
    TituloComponent,
    ListaComponent,
    CancionesTablaComponent,
    BannerListasComponent,
    AlbumComponent,
    ArtistaComponent,
    GeneroComponent,
    RegistroComponent,
    AgregarCancionComponent,
    CancionComponent,
    CrearListaComponent,
    PerfilComponent
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
    DynamicDialogModule,
    ConfirmDialogModule,
    MultiSelectModule,
    AutoFocusModule
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
    DialogService,
    LoginGuardian,
    FavoritoService,
    ConfirmationService
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
