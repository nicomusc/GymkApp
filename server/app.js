const path = require('path');
const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const app = express();


// connecting to db
mongoose.set('useUnifiedTopology', true);
mongoose.set('useCreateIndex', true);
mongoose.connect('mongodb://localhost/crud-mongo',  { useNewUrlParser: true }) //nom de la bbdd
    .then(db => console.log('DB connected!'))//promesa
    .catch(err => console.log(err));

//middleware
app.use(express.urlencoded({ extended: false }));
app.use(express.json());


//Importando Rutas

app.use('/', require('./routes/home'));
app.use('/user', require('./routes/user')); 
app.use('/map', require('./routes/map')); 
app.use('/game', require('./routes/game'));
app.use('/prova', require('./routes/prova')); //de prova -> a borrar

//settings
app.set('port', process.env.PORT || 3001);

// Middlewares. They execute when accessing the route.
/*
app.use('/user', () => {
    console.log('This is a middleware running');
    // Es pot usar per autentificar!
});
*/
//


// stating the server
app.listen(app.get('port'), () => {
console.log(`Server on port ${app.get('port')}`);
});
/*app.listen(3001, () => {
    console.log('Server running at port: 3001');
});*/