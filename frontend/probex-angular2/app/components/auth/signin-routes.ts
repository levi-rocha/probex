import { RouterConfig } from '@angular/router'; 
import { SigninComponent } from './signin-component';
import { SignoutComponent } from './signout-component';
import { AuthGuard } from '../../auth-guard';
import { SigninService } from '../../services/signin-service';

export const SigninRoutes: RouterConfig = [
	{ path: 'signin', component: SigninComponent }, 
	{ path: 'signout', component: SignoutComponent }
];

export const AUTH_PROVIDERS = [ AuthGuard, SigninService ];