import Konva from "Konva";

class Arrow{
    shape1: Konva.Shape
    shape2: Konva.Shape
    layer: Konva.Layer
    arrow: Konva.Line

    constructor(layer: Konva.Layer, shape1: Konva.Shape , shape2: Konva.Shape){
        this.shape1 = shape1
        this.shape2 = shape2
        this.layer = layer 
        this.arrow = this.Arrow()
        layer.add(this.arrow)
        this.layer.draw();


    }

    Arrow(){
        let pos1 = this.shape1.getAbsolutePosition()
        let pos2 = this.shape1.getAbsolutePosition()

        var arrow = new Konva.Line({
            points: [pos1.x , pos1.y  , pos2.x , pos2.y ],
            pointerLength: 10,
            pointerWidth: 10,
            fill: 'black',
            stroke: 'black',
            strokeWidth: 4
        });
        return arrow 


    }

    public update(){
        let pos1 = this.shape1.getAbsolutePosition()
        let pos2 = this.shape1.getAbsolutePosition()
        var p=[pos1.x , pos1.y  , pos2.x , pos2.y ];
        this.arrow.setAttr("points", p) ;
        this.layer.draw();

    }





}
export default Arrow;
