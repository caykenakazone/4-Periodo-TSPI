import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from 'firebase/messaging';

const firebaseConfig = {
  apiKey: "AIzaSyBEMwKR0hcshU-8qcSm_5aW7NMlFrgyE7o",
  authDomain: "push-notification-class-iftm.firebaseapp.com",
  projectId: "push-notification-class-iftm",
  storageBucket: "push-notification-class-iftm.appspot.com",
  messagingSenderId: "930975366131",
  appId: "1:930975366131:web:cba7a8486265b4fee2b086"
}; 

const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

export const requestPermission = () => {

    console.log("Requesting User Permission......");
    Notification.requestPermission().then((permission) => {

      if (permission === "granted") {

        console.log("Notification User Permission Granted."); 
        return getToken(messaging, { vapidKey: "BJ0lN3bXZbx-AED1wFpDFZdPGRAjhQ3EUUxVAwzzPBBMtpsUcVMikyN2vx4vaoZZjaw3zNhB37X4npbGKL45kUY" })
          .then((currentToken) => {

            if (currentToken) {

              console.log('Client Token: ', currentToken);
            } else {
              
              console.log('Failed to generate the app registration token.');
            }
          })
          .catch((err) => {

            console.log('An error occurred when requesting to receive the token.', err);
          });
      } else {

        console.log("User Permission Denied.");
      }
    });
  }

requestPermission();

export const onMessageListener = () =>
  new Promise((resolve) => {
    onMessage(messaging, (payload) => {
      resolve(payload);
    });
});