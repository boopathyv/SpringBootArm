import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-about-upsc',
  templateUrl: './about-upsc.component.html',
  styleUrls: ['./about-upsc.component.css']
})
export class AboutUpscComponent implements OnInit {

  constructor(private route: ActivatedRoute, private titleService: Title, private metaTag: Meta) { }

  ngOnInit() {

    // let self = this;
    // this.route.data.subscribe(function(routeData) {
    //   console.log("route data = ", routeData);
    //   self.titleService.setTitle(routeData.title);
    //   self.metaTag.addTags([{name: "keyword", content: routeData.meta}, {name: "description", content: routeData.description}]);
    // })
  }

}
