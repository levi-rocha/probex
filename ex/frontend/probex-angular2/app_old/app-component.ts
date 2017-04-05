import {Component} from 'angular2/core';
import {FormUserComponent} from './form-user-component';

@Component({
  selector: 'my-app',
  template: '<form-user></form-user>',
  directives: [FormUserComponent]
})
export class AppComponent{}