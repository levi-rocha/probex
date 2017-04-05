import { provideRouter, RouterConfig } from '@angular/router'; 
import { AlunosRoutes } from './components/alunos/alunos-routes';
import { LoginRoutes } from './components/autenticacao/login-routes';
import { AUTH_PROVIDERS } from './components/autenticacao/login-routes';

export const routes: RouterConfig = [
	...AlunosRoutes,
	...LoginRoutes
]; 

export const APP_ROUTER_PROVIDERS = [ 
	provideRouter(routes),
	AUTH_PROVIDERS
];