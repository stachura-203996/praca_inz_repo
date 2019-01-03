import {Component, Input, OnInit} from '@angular/core';

import {CompanyService} from "../../../administration/company/company.service";
import {Router} from "@angular/router";

import {DepartmentService} from "../department.service";
import {StructureAddElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-department-add',
  templateUrl: './department-add.component.html',
  styleUrls: ['./department-add.component.scss']
})
export class DepartmentAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement= new StructureAddElement;

    companies: StructureListElement[];

    selectedOption: string;
    tmp:StructureListElement;
    constructor(private companyService:CompanyService,private departmentService:DepartmentService,private translate:TranslateService,private router:Router) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getCompanies();
    }

    getCompanies(){
        this.companyService.getAll().subscribe(companyListElement=> {this.companies=companyListElement});
    }

    departmentAdd(){
        this.tmp=this.companies.find(x=>x.name==this.selectedOption);
        // this.structureAddElement.companyId=this.tmp.id;
        this.departmentService.createDepartment(this.structureAddElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/departments');
        });
    }

    clear() {
        this.structureAddElement=new StructureAddElement();
    }

}
