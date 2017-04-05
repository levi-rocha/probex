import { provideRouter, RouterConfig } from '@angular/router'; 
import { UserRoutes } from './components/users/user-routes';
import { LoginRoutes } from './components/autenticacao/login-routes';
import { AUTH_PROVIDERS } from './components/autenticacao/login-routes';

export const routes: RouterConfig = [
	...UserRoutes,
	...LoginRoutes
]; 

export const APP_ROUTER_PROVIDERS = [ 
	provideRouter(routes),
	AUTH_PROVIDERS
];