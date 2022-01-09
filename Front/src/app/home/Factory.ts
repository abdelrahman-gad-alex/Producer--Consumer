import Konva from "Konva";
import Arrow from "./arrow";

export interface  Factory{
    layer: Konva.Layer
    arrows: Arrow[]
    machineGroup: Konva.Group
    ID:string 
    inn: string[]
    out: string[]
    color: string 

}
export default Factory;

