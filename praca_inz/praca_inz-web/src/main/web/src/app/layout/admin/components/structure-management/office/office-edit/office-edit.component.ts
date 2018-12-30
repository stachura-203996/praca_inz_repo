import {Component, OnInit} from '@angular/core';
import {StructureEditElement, StructureListElement} from "../../../../../../models/structure-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {CompanyService} from "../../../administration/company/company.service";
import {TranslateService} from "@ngx-translate/core";
import {DepartmentService} from "../../department/department.service";
import {OfficeService} from "../office.service";

@Component({
    selector: 'app-office-edit',
    templateUrl: './office-edit.component.html',
    styleUrls: ['./office-edit.component.scss']
})
export class OfficeEditComponent implements OnInit {

    structureEditElement: StructureEditElement;

    departments: StructureListElement[];

    constructor(
        private route: ActivatedRoute,
        private departmentService: DepartmentService,
        private translate: TranslateService,
        private officeService: OfficeService,
        private router: Router
    ) {
        this.translate.addLangs(['en', 'pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');

    }

    ngOnInit() {
        this.getOffice();
        this.getDepartments();
    }

    getOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.officeService.getOfficeEdit(id).subscribe(x => this.structureEditElement = x);
    }

    getDepartments() {
        this.departmentService.getAll().subscribe(departmentListElement => {
            this.departments = departmentListElement
        });
    }

    officeUpdate() {
        this.officeService.updateOffice(this.structureEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/offices');
        });
    }

    clear() {
        this.structureEditElement = new StructureEditElement;
    }

}
