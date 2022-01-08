import { Component, OnInit } from '@angular/core';
import Konva from 'Konva';

class Shapes{
    shape: any
    clor:string="transparent"
    
    createShape(name: string, color: string, x:number, y:number ,stroke:number)
    {

        switch (name){
            case 'circle':{
                this.shape = this.circle(color, x, y,stroke)
                return this.shape
            }
            case 'ellipse':{
                this.shape = this.ellipse(color, x, y,stroke)
                return this.shape
            }

            case 'rectangle':{
                this.shape = this.rectangle(color, x, y,stroke)
                return this.shape
            }

            case 'square':{
                this.shape = this.square(color, x, y,stroke)
                return this.shape
            }

            case 'triangle':{
                this.shape = this.triangle(color, x, y,stroke)
                return this.shape
            }

            case 'line':{
                this.shape = this.line(color, x, y,stroke)
                return this.shape
            }

        }

    }

    circle( color:string, x:number, y:number ,stroke:number)
    {                            
      var cir = new Konva.Circle({
        fill:"rgb(255, 200, 0)",
        x: x,
        y: y,
        scaleX: 0.999999,
        scaleY: 0.999999,
        skewX: 1e-17,
        rotation: 1e-17,
        radius:40,
        stroke:color,
        strokeWidth:3,
        draggable:true,
        name:'rect',
        id: "-1"
      });
      return cir
    }

    rectangle(color:string, x:number, y:number,stroke:number)
    {
        var rect = new Konva.Rect({
            fill: 'lightblue',
            x:x,
            y:y,
            scaleX: 0.999999,
            scaleY: 0.999999,
            skewX: 1e-17,
            rotation: 1e-17,
            width: 75,
            height: 75,
            stroke: color,
            strokeWidth: stroke,
            draggable:true,
            name:'rect',
            id: "-1"

          });
          return rect
    }

    square(color:string, x:number, y:number,stroke:number)
    {
        var sq = new Konva.Rect({
            fill:this.clor,
            x:x,
            y:y,
            scaleX: 0.999999,
            scaleY: 0.999999,
            skewX: 1e-17,
            rotation: 1e-17,
            width: 100,
            height: 100,
            stroke: color,
            strokeWidth: stroke,
            draggable:true,
            name:'rect',
            id: "-1"
          });
          return sq
    }

    triangle(color:string, x:number, y:number,stroke:number)
    {
        var tri = new Konva.RegularPolygon({
            fill:this.clor,
            x:x,
            y:y,
            scaleX: 0.999999,
            scaleY: 0.999999,
            skewX: 1e-17,
            rotation: 1e-17,
            sides: 3,
            radius: 70,
            stroke: color,
            strokeWidth: stroke,
            draggable:true,
            name:'rect',
            id: "-1"

        });
        return tri
    }

    ellipse(color:string, x:number, y:number,stroke:number)
    {
        var elps = new Konva.Ellipse({
            fill:this.clor,
            x:x,
            y:y,
            scaleX: 0.999999,
            scaleY: 0.999999,
            skewX: 1e-17,
            rotation: 1e-17,
            radiusX: 100,
            radiusY: 50,
            stroke: color,
            strokeWidth: stroke,
            draggable:true,
            name:'rect',
            id: "-1"

        });
        return elps
    }

    line(color: string, x:number, y:number ,stroke:number){
        var line = new Konva.Line({
            x:1e-17,
            y:1e-17,
            points: [x,y,x+150,y],
            scaleX: 0.999999,
            scaleY: 0.999999,
            skewX: 1e-17,
            rotation: 1e-17,
            stroke: color,
            tension: 1,
            draggable: true,
            strokeWidth: stroke,
            name:'rect' , 
            id: "-1"

        });

        return line

    }


    
}
export default Shapes;
