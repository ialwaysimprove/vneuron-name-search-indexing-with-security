import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Person } from '../person';
import { SearchService } from '../search.service';

@Component({
  selector: 'ngx-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      'firstname': new FormControl('Firstname', Validators.required),
      'lastname': new FormControl('Lastname', Validators.required)
    })
  }

  onSubmit() {
    let wholeName = this.searchForm.value['firstname'] + ' ' + this.searchForm.value['lastname'];
    this.searchService.newSearch.next(wholeName);
    console.log(wholeName);

  }
  
  
}
