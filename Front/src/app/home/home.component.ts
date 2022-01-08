import { Component, OnInit ,ViewChild,ElementRef } from '@angular/core';
import Konva from 'Konva';
import Operation from "./operation";
import Arrow from "./arrow";

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

    stage!: Konva.Stage;
    layer!: Konva.Layer;

    drawingArrow : boolean = false
    shape1!: Konva.Shape
    shape2!: Konva.Shape

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

        if (e.target !==this.stage){
         inn = true
          return
        }
        this.Selecting.mouseDown(e , this.stage)
        
        
      });

      this.stage.on('mousemove', (e) => {
        inn= false    
        this.Selecting.mouseMove(e , this.stage)
         
              
      
      });

      this.stage.on('mouseup', (e) => {

        this.Selecting.mouseUp(e , this.stage)
        
      });

      this.stage.on('click',  (e)=> {
        this.Selecting.click(e , this.stage)
        if(!this.drawingArrow){
          this.shape1 = this.Selecting.selectedShapes[0]
          this.drawingArrow = true
        }else{
          this.shape2 = this.Selecting.selectedShapes[0]
          let arr = new Arrow(this.layer, this.shape1, this.shape2)
          this.drawingArrow=false
        }
        
      }); 
      
      
    }

    //for doing the event
  
    
    create(name:string)
    {

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
        fill: 'lightblue',
        name:"rect"

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
        fill: 'red',
        name:"rect"

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
      this.b =shp
      this.addSelection()
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