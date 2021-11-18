import { useState } from "react";
import "./App.css";
import axios from 'axios';
import topoloji from "./img/topoloji2.png";

function App() {
  const [callerPodId, setCallerPodId] = useState("...");
  const [callerServiceName, setCallerServiceName] = useState("...");
  const [callerTime, setCallerTime] = useState("...");
  const [callerUri, setCallerUri] = useState("...");

  const [callmePodId, setCallmePodId] = useState("...");
  const [callmeServiceName, setCallmeServiceName] = useState("...");
  const [callmeTime, setCallmeTime] = useState("...");
  const [callmeUri, setCallmeUri] = useState("...");

  function getPod() {
    /*
      axios.get({uri:"http://192.168.49.2:31726/caller/ping", headers:{Host: 'caller.caller-callme.com'}})
      .then(response => console.log(response));
      
      .then(response => {
        setCallerPodId(response.data.podId);
        setCallerServiceName(response.data.serviceName);
        setCallerTime(response.data.time);
        setCallerUri(response.data.uri);
        console.log("first request");
      });
      */
      axios.get("http://192.168.49.2:31726/caller/ping")
      .then(response => {
        setCallerPodId(response.data.podId);
        setCallerServiceName(response.data.serviceName);
        setCallerTime(response.data.time);
        setCallerUri(response.data.uri);
        console.log("first request");
      });

    setTimeout(() => {
      axios.get("http://192.168.49.2:31726/caller/ping-callme")
      .then(response => {
        setCallmePodId(response.data.podId);
        setCallmeServiceName(response.data.serviceName);
        setCallmeTime(response.data.time);
        setCallmeUri(response.data.uri);
        console.log("second request");
      });
    }, 1500);
  }
  return (
    <>
      <div className="container-wrap">
        <div className="container">
          <div className="data-container">
            <div className="glass-container">
              <button className="btn" onClick={getPod}>
                API Request
              </button>
              <span className="font span">
                BACKEND FIRST CONTAINER
                <br /> Service Name: {callerServiceName}
                <br /> POD_ID: {callerPodId}
                <br /> Time: {callerTime}
                <br /> URI: {callerUri}
              </span>
              <span className="font span ">
                BACKEND SECOND CONTAINER
                <br /> Service Name: {callmeServiceName}
                <br /> POD_ID: {callmePodId}
                <br /> Time: {callmeTime}
                <br /> URI: {callmeUri}
              </span>
            </div>
            <img alt="topoloji" src={topoloji} height="70%"></img>
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
