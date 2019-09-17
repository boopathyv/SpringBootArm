import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import { ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import {NeedAuthGuard} from './auth.guard';
import {NgxPaginationModule} from 'ngx-pagination';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import {DataTableModule} from "angular-6-datatable";


import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { AboutUpscComponent } from './upsc/about-upsc/about-upsc.component';
import { UpscFaqComponent } from './upsc/upsc-faq/upsc-faq.component';
import { MainsComponent } from './upsc/courses-offered/mains/mains.component';
import { FreshersMainsComponent } from './upsc/courses-offered/freshers-mains/freshers-mains.component';
import { OptionalsComponent } from './upsc/courses-offered/optionals/optionals.component';
import { CrashCourseComponent } from './upsc/courses-offered/crash-course/crash-course.component';
import { TestSeriesComponent } from './upsc/courses-offered/test-series/test-series.component';
import { InterviewComponent } from './upsc/courses-offered/interview/interview.component';
import { PrelimsCumMainsComponent } from './upsc/courses-offered/prelims-cum-mains/prelims-cum-mains.component';
import { AboutTnpscComponent } from './tnpsc/about-tnpsc/about-tnpsc.component';
import { TnpscFaqComponent } from './tnpsc/tnpsc-faq/tnpsc-faq.component';
import { Group1Component } from './tnpsc/courses-offered/group1/group1.component';
import { Group2Component } from './tnpsc/courses-offered/group2/group2.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { ForgotpassComponent } from './component/forgotpass/forgotpass.component';

import { TnpscmaterialsComponent } from './course-materials/tnpscmaterials/tnpscmaterials.component';
import { PrelimsmaterialsComponent } from './course-materials/prelimsmaterials/prelimsmaterials.component';
import { MainsmaterialsComponent } from './course-materials/mainsmaterials/mainsmaterials.component';
import { CivilPrelimsComponent } from './aim/civil-prelims/civil-prelims.component';
import { CivilMainsComponent } from './aim/civil-mains/civil-mains.component';
import { PrelimsQuizComponent } from './aim/prelims-quiz/prelims-quiz.component';
import { MainsQaComponent } from './aim/mains-qa/mains-qa.component';
import { FortnightCompilationsComponent } from './aim/fortnight-compilations/fortnight-compilations.component';
import { OnlinetestComponent } from './aim/onlinetest/onlinetest.component';
import { AimscivilSubmenuComponent } from './aim/components/aimscivil-submenu/aimscivil-submenu.component';
import { AimComponent } from './aim/aim.component';
import { ChangePasswordComponent } from './component/change-password/change-password.component';
import { ContactUsComponent } from './component/contact-us/contact-us.component';
import { SubscribeComponent } from './component/subscribe/subscribe.component';
import { TimeOutComponent } from './aim/onlinetest/components/time-out/time-out.component';
import { TestInstructionComponent } from './aim/test-instruction/test-instruction.component';
import { UpscSubmenuComponent } from './upsc/component/upsc-submenu/upsc-submenu.component';
import { ErrorPageComponent } from './component/error-page/error-page.component';
import { SafePipe } from './safe.pipe';
import { AnswerQuestionsComponent } from './aim/answer-questions/answer-questions.component';
import { StudentAnswersComponent } from './aim/student-answers/student-answers.component';
import { CanActivatePage } from './services/can-activate';
import { CanDeActivatePage } from './services/can-deactivate';
import { PrelimsTestComponent } from './test-series/prelims-test/prelims-test.component';
import { MainsTestComponent } from './test-series/mains-test/mains-test.component';
import { TestSeriesPage } from './test-series/test-series.component';

/*
const appRoutes: Routes = [
  { path:'', component:IndexComponent },
  { path:'login', component:LoginComponent },
  { path:'register', component:RegisterComponent },
  { path:'forgotpass', component:ForgotpassComponent },
  { path:'about-upsc', component:AboutUpscComponent },
  { path:'upsc-faq', component:UpscFaqComponent},
  { path:'prelims-cum-mains', component:PrelimsCumMainsComponent },
  { path:'mains', component:MainsComponent },
  { path:'freshers-mains', component:FreshersMainsComponent },
  { path:'optionals', component:OptionalsComponent },
  { path:'crash-course', component:CrashCourseComponent },
  { path:'test-series', component:TestSeriesComponent },
  { path:'interview', component:InterviewComponent },
  { path:'prelims', component:PrelimsComponent,
    canActivate:[NeedAuthGuard] 
  },
  { path:'prelims-metirials', component:PrelimsMetirialsComponent },
  { path:'mains-metirials', component:MainsMetirialsComponent },
  { path:'tnpsc-metirials', component:TnpscMetirialsComponent },
  { path:'tnpsc', 
    children:[
        {path:'about-tnpsc', component:AboutTnpscComponent},
        {path:'courses-offered', children:[
        {path:'group1', component:Group1Component},
        {path:'group2', component:Group2Component},
      ]},
    ]
  }
]; */

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    AboutUpscComponent,
    UpscFaqComponent,
    MainsComponent,
    FreshersMainsComponent,
    OptionalsComponent,
    CrashCourseComponent,
    TestSeriesComponent,
    InterviewComponent,
    PrelimsCumMainsComponent,
    AboutTnpscComponent,
    TnpscFaqComponent,
    Group1Component,
    Group2Component,
    LoginComponent,
    RegisterComponent,
    ForgotpassComponent,   
    TnpscmaterialsComponent,
    PrelimsmaterialsComponent,
    MainsmaterialsComponent,
    CivilPrelimsComponent,
    CivilMainsComponent,
    PrelimsQuizComponent,
    MainsQaComponent,
    FortnightCompilationsComponent,
    OnlinetestComponent,
    AimscivilSubmenuComponent,
    AimComponent,
    ChangePasswordComponent,
    ContactUsComponent,
    SubscribeComponent,
    TimeOutComponent,
    TestInstructionComponent,
    UpscSubmenuComponent,
    ErrorPageComponent,
    SafePipe,
    AnswerQuestionsComponent,
    StudentAnswersComponent,
    TestSeriesPage,
    PrelimsTestComponent,
    MainsTestComponent
  ],
  providers: [CanActivatePage, CanDeActivatePage],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    NgbModule,
    FormsModule,
    DataTableModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
