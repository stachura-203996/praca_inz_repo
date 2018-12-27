import {Component, Input, OnInit} from '@angular/core';

import {CompanyService} from "../../../administration/company/company.service";
import {DepartmentService} from "../department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureEditElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";


@Component({
    selector: 'app-department-edit',
    templateUrl: './department-edit.component.html',
    styleUrls: ['./department-edit.component.scss']
})
export class DepartmentEditComponent implements OnInit {

    structureEditElement: StructureEditElement;

    companies: StructureListElement[];

    constructor(private route: ActivatedRoute,private companyService: CompanyService,private translate:TranslateService, private departmentService: DepartmentService, private router: Router) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');

    }

    ngOnInit() {
        this.getDepartment();
        this.getCompanies();
    }

    getDepartment() {
        const id = this.route.snapshot.paramMap.get('id');
        this.departmentService.getDepartmentEdit(id).subscribe(x=>this.structureEditElement=x);
    }

    getCompanies() {
        this.companyService.getAll().subscribe(companyListElement => {
            this.companies = companyListElement
        });
    }

    departmentUpdate() {
        this.departmentService.updateDepartment(this.structureEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/departments');
        });
    }

    clear() {
        this.structureEditElement=new StructureEditElement;
    }

}
