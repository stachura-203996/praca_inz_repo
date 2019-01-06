import {Component, Input, OnInit} from '@angular/core';
import {CompanyService} from "../company.service";
import {ActivatedRoute, Router} from "@angular/router";
import {
    StructureAddElement,
    StructureEditElement,
    StructureListElement
} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {DepartmentService} from "../../../structure-management/department/department.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-company-edit',
  templateUrl: './company-edit.component.html',
  styleUrls: ['./company-edit.component.scss']
})
export class CompanyEditComponent implements OnInit {

    structureEditElement: StructureEditElement;



    constructor(private route: ActivatedRoute,private companyService: CompanyService,private translate:TranslateService, private departmentService: DepartmentService, private router: Router) {


    }

    ngOnInit() {
        this.getCompany();
    }

    getCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.companyService.getCompanyEdit(id).subscribe(x=>this.structureEditElement=x);
    }

    companytUpdate() {
        this.companyService.updateCompany(this.structureEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/companies');
        });
    }

    clear() {
        this.structureEditElement=new StructureEditElement;
    }
}
