import { Component, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { SmartTableData } from '../../../../@core/data/smart-table';
import { Person } from '../person';
import { SearchService } from '../search.service';

@Component({
  selector: 'ngx-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.scss']
})
export class SearchResultsComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource(); // So the entire thing revolves around this one I guess? How to replace this wit ha Server Data Source...
  wholeName = '';
  matchesFound = 0;
  pageSize = 25;

  constructor(
    private service: SmartTableData, // How the x is this bullshit working anyways?
    private searchService: SearchService) {
    
      //const data = this.service.getData();
      //this.source.load(data);
    // This data is loaded only at the beginning :'(), let's make it in a way that it loads a match all search?
  }



  ngOnInit(): void {
    this.searchService.newSearch.subscribe(
      (searchTerm: string) => {
        this.wholeName = searchTerm;
        this.searchService.searchData(searchTerm)
        .pipe()
        .subscribe(
          (results) => {
            this.matchesFound = this.searchService.resultsFound; // How to make pagination possible?
            this.source.load(results)
          }
        );
      }
    )
  }

  settings = {
    // actions: false,
    // hideSubHeader: true,
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },


    columns: {
      entity_id: {
        title: 'ID',
        type: 'string'
      },
      source_document: {
        title: 'Source',
        type: 'string'
      },
      id_in_document: {
        title: 'ID In Document',
        type: 'string'
      },
      entity_type: {
        title: 'Type',
        type: 'string'
      },
      primary_name: {
        title: 'Primary Name',
        type: 'string'
      },
      whole_names: {
        title: 'Whole Names',
        type: 'string'
      },
      score: {
        title: 'Score',
        type: 'number'
      }
    },

    pager : {
      perPage :this.pageSize
    },
  };
  
}
