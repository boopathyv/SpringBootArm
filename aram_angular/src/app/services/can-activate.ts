import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { Meta, Title } from "@angular/platform-browser";

@Injectable()
export class CanActivatePage implements CanActivate {

    constructor(private titleService: Title, private metaTag: Meta) { }

    canActivate(route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): boolean {

        let self = this;

        console.log("route = ", route);
        
        
        self.titleService.setTitle(route.data.title);
        self.metaTag.updateTag({ name: "keyword", content: route.data.meta });
        self.metaTag.updateTag({ name: "description", content: route.data.description });

        return true;

        // return true;

    }
}