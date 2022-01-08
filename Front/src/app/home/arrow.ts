import Konva from "Konva";

class Arrow{
    shape1: Konva.Shape
    shape2: Konva.Shape
    layer: Konva.Layer
    arrow: Konva.Arrow

    constructor(layer: Konva.Layer, shape1: Konva.Shape , shape2: Konva.Shape){
        this.shape1 = shape1
        this.shape2 = shape2
        this.layer = layer 
        this.arrow = this.Arrow()
        layer.add(this.arrow)
        this.layer.draw();


    }

    Arrow(){
        var arrow = new Konva.Arrow({
            points: [this.shape1.getAttr("x") , this.shape1.getAttr("y")  ,this.shape2.getAttr("x")  ,this.shape2.getAttr("y") ],
            pointerLength: 10,
            pointerWidth: 10,
            fill: 'black',
            stroke: 'black',
            strokeWidth: 4
        });
        return arrow 


    }

    public update(){
        var p=[this.shape1.getAttr("x") , this.shape1.getAttr("y")  ,this.shape2.getAttr("x")  ,this.shape2.getAttr("y") ];
        this.arrow.setAttr("points", p) ;
        this.layer.draw();

    }





}
export default Arrow;
