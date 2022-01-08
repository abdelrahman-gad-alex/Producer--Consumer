import { Component, OnInit ,ViewChild,ElementRef } from '@angular/core';
import Konva from 'Konva';
import Operation from "./operation";
import Selecting from "./selecting"
import { HttpClient } from '@angular/common/http';
import { observable } from 'rxjs';
import {HotkeysService , Hotkey} from 'angular2-hotkeys';

@Component({
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
  })

  export class homecomponent implements OnInit {
    m:any
    q:any
    b:any
    operations: any = new Operation
    Selecting: any = new Selecting
    drawMode: boolean = false
    drawflag: boolean = false
    copyflag: boolean = false
    stage!: Konva.Stage;
    layer!: Konva.Layer;
 
    color: string = 'black'
   stroke:number=3
   @ViewChild('menu ') menu!:ElementRef
   contextMenu(e:any)
   {
     console.log(e.pageX)
     console.log(e.pageY)
     e.preventDefault()
     this.menu.nativeElement.style.display="block"
     this.menu.nativeElement.style.top=e.pageY+"px"
     this.menu.nativeElement.style.left=e.pageX+"px"
   }
   disappear()
   {
     this.menu.nativeElement.style.display="none"
   }
   


    ngOnInit(): void {  
        
      this.stage = new Konva.Stage({  //create the stage
        container: 'container',
        width: window.innerWidth,
        height: window.innerHeight
      });
      
      this.layer = new Konva.Layer;
      this.stage.add(this.layer);

      
      this.Selecting.initiate() 
        var inn=false

        this.stage.on('mousedown', (e) => {
        if(this.drawMode){
            this.drawflag = true
        }else{
          if (e.target !==this.stage){
             inn = true
            return
          }
          this.Selecting.mouseDown(e , this.stage)
        
        }
      });

      this.stage.on('mousemove', (e) => {
        if(this.drawMode){
        }else{
          
          inn= false
          
          this.Selecting.mouseMove(e , this.stage)
         
        }        
      
      });

      this.stage.on('mouseup', (e) => {
        if(this.drawMode){
        }else{
          
          this.Selecting.mouseUp(e , this.stage)
          if(this.Selecting.selectedShapes.length !=0){
           
            if(!inn && this.Selecting.move && this.Selecting.tr.nodes().length != 0){
              
            }
          }
        } 
      });

      this.stage.on('click',  (e)=> {
        if(e.evt.which == 1){
          this.Selecting.click(e , this.stage)
        }
      }); 
      
      
    }

    //for doing the event
  
    
    create(name:string)
    {

      this.Selecting.editDragable(false)
      var shift = this.operations.checkForShift(this.layer , name)
      var shp = new Konva.Group({        
        x: 150+shift, 
        y: 150+shift, 
        width: 130,
        height: 25,
        rotation: 0, 
        draggable: true,
        name:"rect"
      })
      if (name=="rectangle"){
      shp.add(new Konva.Rect({
        
        width: 75,
        height: 75,
        stroke: "rgb(0,0,0)",
        strokeWidth: 3,
        fill: 'lightblue'
    }));
    shp.add(new Konva.Text({
      x:15,
      y:5,
      text:"Q"+this.q,
      fontFamily: 'Calibri',
      fontSize: 30,
      fontStyle:('bold'),

      fill: '#000',
      padding: 5,
      align: 'center'
    }));
    this.q++;
     }
      else{
      shp.add(new Konva.Circle({
        
        radius:75/2,
        stroke: "rgb(0,0,0)",
        strokeWidth: 3,
        fill: 'red'
    }));
      shp.add(new Konva.Text({
        x:-25,
        y:-20,
        text:"M"+this.m,
        fontSize: 30,
        fontStyle:('bold'),
        fontFamily: 'Calibri',
        fill: '#000',
        padding: 5,
        align: 'center'
      }));
      this.m++;
    }
      this.layer.add(shp)
      this.layer.draw()
    
  }
    addSelection(){
      this.Selecting.selectedShapes = [this.b]
      this.layer.add(this.Selecting.tr)
      this.Selecting.tr.nodes([this.b])
      this.layer.add(this.Selecting.selected)
    }
 

    clear()
    {
      this.layer.removeChildren()
      this.q=0
      this.m=0
    }

    constructor(public http: HttpClient,private _hotkeysService: HotkeysService){ 
      this.q=0
      this.m=0
      this._hotkeysService.add(new Hotkey('r', (event: KeyboardEvent): boolean => {
        this.create("rectangle");
        return false; // Prevent bubbling
      }));
      this._hotkeysService.add(new Hotkey('c', (event: KeyboardEvent): boolean => {
        this.create("circle");
        return false; // Prevent bubbling
      }));

    }
  }