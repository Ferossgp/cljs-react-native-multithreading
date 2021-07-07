import { runOnJS } from "react-native-reanimated";
import "react-native-multithreading";

var a;

function callGlobal(argument) {
  a = argument;
}

const ___WORKLET_BUNDLE___ = function(method, args){
  "worklet";
  var shadow$worklet$export = null;
  
  //CLJS-HERE

  return shadow$worklet$export[method](args);
}

export function getTheA(){
  return a;
}

export function main(method){
  return function(args){
    return spawnThread(function(){
      "worklet";
      return ___WORKLET_BUNDLE___(method, args)
    })
  }
}
