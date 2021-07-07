import { runOnJS } from "react-native-reanimated";
import "react-native-multithreading";

var store;
var writer;
var reader;

function writeToStore(argument) {
  writer(store, argument)
}

function writeFromUI(data) {
  "worklet";
  runOnJS(writeToStore)(data)
}

export function initStore(s, w, r) {
  store = s;
  writer = w;
  reader = r;
}

const ___WORKLET_BUNDLE___ = function(method, args){
  "worklet";
  var shadow$worklet$export = null;
  
  //CLJS-HERE

  return shadow$worklet$export[method](args);
}

export function main(method){
  return function(args){
    return spawnThread(function(){
      "worklet";
      return ___WORKLET_BUNDLE___(method, args)
    })
  }
}
