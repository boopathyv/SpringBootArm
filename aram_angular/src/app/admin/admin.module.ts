import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {NgxPaginationModule} from 'ngx-pagination';

import { AdminRoutingModule } from './admin-routing.module';
import { LoginComponent } from './login/login.component';
import { LatestNewsComponent } from './latest-news/latest-news.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { StudentsComponent } from './students/students.component';

@NgModule({
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule
  ],
  declarations: [LoginComponent, LatestNewsComponent, AdminHomeComponent, UploadFileComponent, StudentsComponent]
})
export class AdminModule { }
