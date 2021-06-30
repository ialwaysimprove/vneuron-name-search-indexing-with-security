import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SmartTableData } from '../data/smart-table';

@Injectable()
export class SmartTableService extends SmartTableData {

  data = [
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908555","whole_names":["","RI  WON HO"],"primary_name":"RI  WON HO"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908570","whole_names":["Jang Chang Ha","CHANG CHANG HA"],"primary_name":"CHANG CHANG HA"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908571","whole_names":["Jo Chun Ryong ","CHO   CHUN RYONG"],"primary_name":"CHO   CHUN RYONG"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908565","whole_names":["JO  YONG CHOL","Cho Yong Chol"],"primary_name":"JO  YONG CHOL"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908566","whole_names":["","KIM CHOL SAM"],"primary_name":"KIM CHOL SAM"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908568","whole_names":["","KIM SOK CHOL"],"primary_name":"KIM SOK CHOL"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908558","whole_names":["","Kim Hak Song","KIM SONG CHOL"],"primary_name":"KIM SONG CHOL"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908573","whole_names":["","KIM  SE GON"],"primary_name":"KIM  SE GON"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908564","whole_names":["SON JONG HYOK","Son Min"],"primary_name":"SON JONG HYOK"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908572","whole_names":["","SON   MUN SAN"],"primary_name":"SON   MUN SAN"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908434","whole_names":["Abd-al-Khaliq Badr-al-Din al Huthi","Abu-Yunus","Abd-al-Khaliq al-Huthi","ABD AL-KHALIQ  AL-HOUTHI","‘Abd al-Khaliq Badr al-Din al-Huthi","عبدالخالق الحوثي","Abd al-Khaliq al-Huthi "],"primary_name":"ABD AL-KHALIQ  AL-HOUTHI"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908576","whole_names":["Abu-Julaybib al-Urduni ","Abu-Julaybib ","Iyad al-Toubasi ","Ayyad Nazmi Salih Khalil ","Eyad Nazmi Saleh Khalil","Iyad al-Tubasi ","IYAD NAZMI  SALIH  KHALIL","إياد نظمي صالح خليل ","Abu al-Darda' "],"primary_name":"IYAD NAZMI  SALIH  KHALIL"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908757","whole_names":["Taha al-Khuwayt","Hajji Abd al-Nasr","Hajji Abdelnasser","HAJJI 'ABD AL-NASIR"],"primary_name":"HAJJI 'ABD AL-NASIR"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908443","whole_names":["عبدالملك محمد يوسف عثمان عبد السلام","'Umar al-Tayyar","'Umar al-Qatari","'ABD AL-MALIK MUHAMMAD YUSUF 'UTHMAN 'ABD AL-SALAM","'Abd al-Malik Muhammad Yusif 'Abd-al-Salam"],"primary_name":"'ABD AL-MALIK MUHAMMAD YUSUF 'UTHMAN 'ABD AL-SALAM"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908440","whole_names":["Ashraf Muhammad Yusuf 'Abd-al-Salam","ASHRAF MUHAMMAD YUSUF 'UTHMAN 'ABD AL-SALAM","Ashraf Muhammad Yusif 'Abd al-Salam","اشرف محمد يوسف عثمان عبد السلام","Ashraf Muhammad Yusif 'Uthman 'Abd-al-Salam","Ibn al-Khattab","Khattab"],"primary_name":"ASHRAF MUHAMMAD YUSUF 'UTHMAN 'ABD AL-SALAM"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-110446","whole_names":["","FEREIDOUN ABBASI-DAVANI"],"primary_name":"FEREIDOUN ABBASI-DAVANI"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-2975591","whole_names":["Abdul Aziz Mahsud","عبد العزيز عباسین","ABDUL AZIZ ABBASIN"],"primary_name":"ABDUL AZIZ ABBASIN"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-111900","whole_names":["Abd al-Hadi al-Ansari","نشوان عبد الرزاق عبد الباقي","Abd al-Muhayman","Abu Abdallah","Abdal Al-Hadi Al-Iraqi","Abd Al-Hadi Al-Iraqi","Omar Uthman Mohammed","Abu Ayub","Abdul Hadi Arif Ali","NASHWAN ABD AL-RAZZAQ ABD AL-BAQI","Abdul Hadi al-Taweel"],"primary_name":"NASHWAN ABD AL-RAZZAQ ABD AL-BAQI"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908090","whole_names":["Humam 'Abd al-Khaliq 'Abd al-Rahman","Humam 'Abd-al-Khaliq Rashid","همام عبد الخالق عبد الغفور","HUMAM ABD-AL-KHALIQ ABD-AL-GHAFUR"],"primary_name":"HUMAM ABD-AL-KHALIQ ABD-AL-GHAFUR"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-6908057","whole_names":["","KAMAL MUSTAFA ABDALLAH","كمال مصطفى عبد الله","Kamal Mustafa Abdallah Sultan al-Tikriti"],"primary_name":"KAMAL MUSTAFA ABDALLAH"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-111533","whole_names":["عبد الله محمد رجب عبد الرحمن","ABD ALLAH MOHAMED RAGAB ABDEL RAHMAN","Abu Al-Khayr","Ahmad Hasan","Abu Jihad"],"primary_name":"ABD ALLAH MOHAMED RAGAB ABDEL RAHMAN"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-111301","whole_names":["","عزیز الرحمان عبد الاحد","AZIZIRAHMAN ABDUL AHAD"],"primary_name":"AZIZIRAHMAN ABDUL AHAD"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-111040","whole_names":["Mullah Baradar Akhund","Abdul Ghani Baradar","عبدالغنی برادر عبد الاحمد ترک","ABDUL GHANI BARADAR ABDUL AHMAD TURK"],"primary_name":"ABDUL GHANI BARADAR ABDUL AHMAD TURK"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-111302","whole_names":["","Abdul Qadir","Ahmad Haji","عبدالقدیر بصیر عبد البصير","Abdul Qadir Basir","ABDUL QADEER BASIR ABDUL BASEER","Abdul Qadir Haqqani"],"primary_name":"ABDUL QADEER BASIR ABDUL BASEER"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-110962","whole_names":["نظر محمدعبد البصیر","NAZIR MOHAMMAD ABDUL BASIR","Nazar Mohammad"],"primary_name":"NAZIR MOHAMMAD ABDUL BASIR"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-111449","whole_names":["Majeed, Abdul","Majid, Abdul","","MAJEED ABDUL CHAUDHRY","Majeed Chaudhry Abdul"],"primary_name":"MAJEED ABDUL CHAUDHRY"},
    {"entity_type":"P","entity_id":"UNSecurityCouncil-690726","whole_names":["","ABDUL GHAFAR QURISHI ABDUL GHANI","عبدالغفار قریشی عبد الغنی","Abdul Ghaffar Qureshi"],"primary_name":"ABDUL GHAFAR QURISHI ABDUL GHANI"},
  ];

  // I just need to learn how to 

  constructor () {
    super();
  }

  getData() {
    return this.data;
  }
}
