import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import { ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import {NeedAuthGuard} from './auth.guard';

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
import { LoginComponent } from './component/login/login.component';
import { AimComponent } from './aim/aim.component';
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
import { ChangePasswordComponent } from './component/change-password/change-password.component';
import { TestInstructionComponent } from './aim/test-instruction/test-instruction.component';
import { ErrorPageComponent } from './component/error-page/error-page.component';
import { AnswerQuestionsComponent } from './aim/answer-questions/answer-questions.component';
import { StudentAnswersComponent } from './aim/student-answers/student-answers.component';
import { CanActivatePage } from './services/can-activate';
import { CanDeActivatePage } from './services/can-deactivate';
import { PrelimsTestComponent } from './test-series/prelims-test/prelims-test.component';
import { MainsTestComponent } from './test-series/mains-test/mains-test.component';
import { TestSeriesPage } from './test-series/test-series.component';

const appRoutes: Routes = [
  { path:'', component:IndexComponent },
  { path:'login', component:LoginComponent },
  { path:'register', component:RegisterComponent },
  { path:'forgotpass', component:ForgotpassComponent },
  { path:'change-password', component:ChangePasswordComponent},
 /* { path:'prelims', component:PrelimsComponent,
    canActivate:[NeedAuthGuard] 
  },*/
  { path:'aim',
    component:AimComponent,
  //  redirectTo:"/aim/civil-prelims",
   // canActivateChild:[NeedAuthGuard],
    children:[
      { path:'civil-prelims', component:CivilPrelimsComponent, canActivate: [CanActivatePage], canDeactivate: [CanDeActivatePage], data: {title: "Civil Service Coaching Center | Civil Service Coaching Center in Chennai",
      description : "There are many Civil services coaching centres in Chennai. The biggest problem arises when it comes to choosing one. Aran IAS Academy is a preferred place in Chennai.For best civil service exam coaching",
      meta: "Civil Service Coaching Center ,Civil Service Coaching Center in Chennai,civil service coaching in chennai"} },
      { path:'civil-mains', component:CivilMainsComponent },
      { path:'prelims-quiz', component:PrelimsQuizComponent },
      { path:'mains-qa', component:MainsQaComponent },
      { path:'fortnight-compilations', component:FortnightCompilationsComponent },
      { path:'mocktest', component:TestInstructionComponent, canActivate:[NeedAuthGuard] },
      { path:'onlinetest', component:OnlinetestComponent, canActivate:[NeedAuthGuard] },
      { path:'test-questions', component:AnswerQuestionsComponent, canActivate:[NeedAuthGuard] },
      { path:'test-question/:id/answers', component:StudentAnswersComponent, canActivate:[NeedAuthGuard] }
    ]
  },
  { path:'course-materials',
    children:[
      { path:'prelims-metirials', component:PrelimsmaterialsComponent },
      { path:'mains-metirials', component:MainsmaterialsComponent },
      { path:'tnpsc-metirials', component:TnpscmaterialsComponent, canActivate: [CanActivatePage], canDeactivate: [CanDeActivatePage], data: {title: "TNPSC  coaching centre in chennai | TNPSC Coaching classes in chennai",
      description : "TNPSC  coaching centre in chennai to train well and to get the proper guidance for TNPSC coaching. It is very important that you choose the right institution to study",
      meta: "TNPSC  coaching centre in chennai,TNPSC classes in chennai,tnpsc coaching institute in chennai"} },
    ]
  },
  
  { path:'upsc',
    children:[
      { path:'about-upsc', component:AboutUpscComponent, canActivate: [CanActivatePage], canDeactivate: [CanDeActivatePage], data: {title: "Best  UPSC Coaching Center in Chennai | UPSC Training center in chennai",
      description : "Aram IAS Coaching Institute is the best UPSC coaching centre in Chennai.Among all others, Aram is well-known for its training session and rigorous assessments",
      meta: "UPSC Coaching Center , UPSC coaching center in chennai,famous upsc coaching center,UPSC coaching center near annanngar"} },
      {path:'courses-offered',
         children:[
          { path:'prelims-cum-mains', component:PrelimsCumMainsComponent },
          { path:'mains', component:MainsComponent },
          { path:'freshers-mains', component:FreshersMainsComponent },
          { path:'optionals', component:OptionalsComponent },
          { path:'crash-course', component:CrashCourseComponent },
          { path:'test-series', component:TestSeriesComponent },
          { path:'interview', component:InterviewComponent },
      ]},
      { path:'upsc-faq', component:UpscFaqComponent},
    ]
  },
  { path:'tnpsc', 
    children:[
        {path:'about-tnpsc', component:AboutTnpscComponent},
        {path:'courses-offered',
         children:[
          {path:'group1', component:Group1Component, canActivate: [CanActivatePage], canDeactivate: [CanDeActivatePage], data: {title: "Group 1 Coaching Center in Chennai | Group 1 Training Centres in Chennai",
          description : "Group 1 Coaching Center in Chennai to train well and to get the proper guidance for TNPSC coaching. It is very important that you choose the right institution to study",
          meta: "Group 1 Coaching Center in Chennai , Group 1 Training Centres in Chennai , tnpsc group 1 coaching centre in chennai"}},
          {path:'group2', component:Group2Component, canActivate: [CanActivatePage], canDeactivate: [CanDeActivatePage], data: {title: "Group 2 Training Centres in Chennai | Best tnpsc Coaching Centres in Chennai",
          description : "Group 2 Training Centres in Chennai to train well and to get the proper guidance for TNPSC coaching. It is very important that you choose the right institution to study",
          meta: "Group 2 coaching center in chennai ,tnpsc coaching institute in chennai ,tnpsc coaching center chennai"}},
        ]},
        {path:'tnpsc-faq', component:TnpscFaqComponent},
    ]
  },
  {
    path: 'admin',
    loadChildren: './admin/admin.module#AdminModule'
  },
  {
    path: 'test-series',
    component: TestSeriesPage,
    children: [
      {path: 'prelims-test', component: PrelimsTestComponent, canActivate:[NeedAuthGuard]},
      {path: 'mains-test', component: MainsTestComponent, canActivate:[NeedAuthGuard]}
    ]
  },
  { path: '**', redirectTo: ''}

  
];


@NgModule({
    exports: [RouterModule],
    imports:[RouterModule.forRoot(appRoutes)]
})
export class AppRoutingModule { }
