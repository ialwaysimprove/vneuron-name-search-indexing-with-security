import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
  NbTreeGridModule
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { WatchlistSearchRoutingModule } from './watchlist-search-routing.module';
import { WatchlistSearchComponent } from './watchlist-search.component';
// import { FormInputsComponent } from './form-inputs/form-inputs.component';
import { ElasticsearchComponent } from './elasticsearch/elasticsearch.component';
// import { DatepickerComponent } from './datepicker/datepicker.component';
// import { ButtonsComponent } from './buttons/buttons.component';
import { FormsModule, FormsModule as ngFormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { SearchResultsComponent } from './elasticsearch/search-results/search-results.component';
import { SearchBarComponent } from './elasticsearch/search-bar/search-bar.component';

@NgModule({
  imports: [
    ThemeModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    WatchlistSearchRoutingModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
    Ng2SmartTableModule,
    FormsModule,
    ReactiveFormsModule

  ],
  declarations: [
    WatchlistSearchComponent,
    ElasticsearchComponent,
    SearchResultsComponent,
    SearchBarComponent,
  ],
})
export class WatchlistSearchModule { }
