import Konva from "Konva";
import Arrow from "./arrow";
import Factory from "./Factory" ;
class Queue implements Factory{
    layer: Konva.Layer
    arrows: Arrow[] = []
    machineGroup: Konva.Group
    ID!:string
    inn: string[] =[]
    out: string[]= []
    color = 'lightblue'

    constructor(layer: Konva.Layer, shift:number, q:number){
        this.layer = layer  
        var shp = new Konva.Group({        
            x: 150+shift, 
            y: 150+shift, 
            width: 130,
            height: 25,
            rotation: 0, 
            draggable: true,
            name:"Queue",
            id: "q"+q
          })

          shp.add(new Konva.Rect({
        
            width: 75,
            height: 75,
            stroke: "rgb(0,0,0)",
            strokeWidth: 3,
            fill: this.color,
            name:"Queue",
    
        }));
        shp.add(new Konva.Text({
          x:15,
          y:5,
          text:"Q"+q,
          fontFamily: 'Calibri',
          fontSize: 30,
          fontStyle:('bold'),
          fill: '#000',
          padding: 5,
          align: 'center',
          name:"Queue"

        }));
        shp.add(new Konva.Rect({
            x:0,
            y:75/2,
            width:0,
            height: 0,
            name:"1"

        }))
        shp.add(new Konva.Rect({
            x:75,
            y:75/2,
            width:0,
            height: 0,
            name:"2"

        }))
        shp.add(new Konva.Rect({
            x:75/2,
            y:0,
            width:0,
            height: 0,
            name:"3"

        }))
        shp.add(new Konva.Rect({
            x:75/2,
            y:75,
            width:0,
            height: 0,
            name:"4"

        }))

          this.ID = "q"+q
          this.machineGroup = shp
          this.layer.add(this.machineGroup)
          this.layer.draw();

     
    }



}
export default Queue;