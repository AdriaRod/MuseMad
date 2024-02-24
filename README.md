# MUSEMAD

>### Prototipo en [Figma](https://www.figma.com/file/7Y4QOjUOfIgEKFnunyJNft/MuseMad?type=design&node-id=3%3A33&mode=design&t=neoJ5mivNpkyReKs-1)

## Login
##### En el login, contamos con 2 botónes, uno para iniciar sesión con nuestros credenciales y otro para entrar como invitado a la aplicación, además también contamos con un *TextView* clickable el cual nos mandará a la Activity de Register
<p align="center">
<img src="img/loginv1.png" alt="Imagen del login" width="364" height="720"/>
</p>

## Register
##### En el register contamos con un dos campos para poner nuestros credenciales y un botón para registrarse, que creará nuestro usuario en la Base de datos de *Firebase*
<p align="center">
<img src="img/registerv1.png" alt="Imagen del register" width="364" height="720"/>
</p>

## Home

#### Main
##### En el home principal, contamos con un *AppBarLayout* con el título de la aplicación y un *SearchView* para filtrar los museos. Más abajo tenemos un *RecyclerView* con *CardViews*, las cuales muestran una foto y el nombre del museo, además estas tarjetas son clickables y nos mandarán a otro fragment con información adicional del museo
<p align="center">
<img src="img/homev2.png" alt="Primera vista en el home" width="364" height="720"/>
</p>

##### El *AppBarLayout* se contraerá al bajar en el *RecyclerView* dejando así una vista más clara de las tarjetas con los museos
<p align="center">
<img src="img/home2v1.png" alt="Primera vista en el home" width="364" height="720"/>
</p>

##### Por último también contamos con un *SearchView* funcional para filtrar los museos por su nombre
<p align="center">
<img src="img/homesv.png" alt="Primera vista en el home" width="364" height="720"/>
</p>

#### Settings

#### Detail Museo
##### Al pulsar en una de las tarjetas, nos enviará a una nueva vista en la que podremos ver los detalles del museo además de agregarlo a favoritos usando el botón de la derecha, para así poderlo ver en nuestra vista de favoritos
<p align="center">
<img src="img/museuminfov1.png" alt="Imagen de DetailMuseum" width="364" height="720"/>
</p>

## Mapa

#### Mapa
##### Utilizando la API de *Google Maps* hemos hecho un mapa con distintos símbolos los cuales representan la ubicación de los distintos museos, al pulsar en uno de estos símbolos, podremos abrir *Google Maps* con la dirección de este museo o abrir la vista de *DetailMuseum* desde aqui
<p align="center">
<img src="img/mapav0.png" alt="Imagen del fragment del mapa" width="364" height="720"/>
</p>

## Favoritos

#### Favoritos
##### En el apartado de *Favoritos* veremos los museos que tengamos agregados a favoritos en una vista distinta, para así poderlos buscar más facilmente si es que un museo nos ha gustado o queremos visitarlo próximamente
<p align="center">
<img src="img/favv1.png" alt="Imagen del fragment de favoritos" width="364" height="720"/>
</p>
