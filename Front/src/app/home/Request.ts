import Factory from "./Factory";

class Requests{

    playRequest(MQmap: Map<string,Factory>){
        let q = new Map<string,Map<string,string[]>>()
        let m = new Map<string,Map<string,string[]>>()

        for(let key of MQmap.keys()) {
            let oneq = new Map<string,string[]>()
            oneq.set("in", MQmap.get(key)!.inn )
            oneq.set("out", MQmap.get(key)!.out )
            if(key.charAt(0)=="q"){
                q.set(key, oneq)
            }else{
                m.set(key, oneq)
            }
        }
        console.log(q)


    }



}
export default Requests;
