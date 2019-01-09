import {Component, Input, OnInit} from '@angular/core';
import {CompanyService} from "../company.service";
import {Router} from "@angular/router";
import {StructureAddElement} from "../../../../../../models/structure-elements";


@Component({
    selector: 'app-company-add',
    templateUrl: './company-add.component.html',
    styleUrls: ['./company-add.component.scss']
})
export class CompanyAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement= new StructureAddElement;

    constructor(
        private companyService:CompanyService,
        private router:Router,
    ) {}

    ngOnInit() {}

    companyAdd(){
        this.companyService.createCompany(this.structureAddElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/companies');
        });
    }

    clear() {
      this.structureAddElement=new StructureAddElement();
    }
}
