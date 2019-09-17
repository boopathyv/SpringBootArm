import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LatestNewsComponent } from './latest-news/latest-news.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { StudentsComponent } from './students/students.component';

const routes: Routes = [
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'',
    component: AdminHomeComponent,
    children: [
      {
        path: 'latestNews',
        component: LatestNewsComponent
      },
      {
        path: 'uploadFile',
        component: UploadFileComponent
      },
      {
        path: 'students',
        component: StudentsComponent
      }
    
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
