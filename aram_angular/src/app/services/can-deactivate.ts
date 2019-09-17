import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanDeactivate } from "@angular/router";
import { Meta, Title } from "@angular/platform-browser";
import { Observable } from "rxjs";


export interface CanComponentDeactivate {
    canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

@Injectable()
export class CanDeActivatePage implements CanDeactivate<CanComponentDeactivate> {

    constructor(private titleService: Title, private metaTag: Meta) { }

    canDeactivate(component: CanComponentDeactivate, 
        route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot) {

        this.titleService.setTitle("IAS Academy in Chennai | Best IAS Academy in Chennai | Aram");
        this.metaTag.updateTag({ name: "keyword", content: "IAS  Academy in Chennai, Best Civil Services in Chennai , Best UPSC Coaching in Chennai, Best IAS Academy in Chennai, IAS  Academy in Chennai, IAS coaching institute in Chennai, IAS Coaching in Chennai" });
        this.metaTag.updateTag({ name: "description", content: "Aram IAS Academy is the best IAS academy in Chennai.We are the best IAS coaching center, Best Civil services and UPSC coaching in Chennai" });

        return true;

    }
}