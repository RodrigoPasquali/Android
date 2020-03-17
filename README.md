MELISearcher (by Rodrigo Pasquali)

CONSIGA

-Desarrollar una app que utilice las APIs de Mercadolibre, con dos secciones:
1)Search: buscador de productos y listado de respuestas.
2)Product Page: detalle de un producto (al cual se debería poder acceder al tappear uno de los items en el resultado del search).

ENTREGA
-Se logró cumplir con los requisitos básicos solicitados(puntos 1 y 2 de consignas).
1)Searcher --> La app inicia como pantalla inicial una vista que permite ingresar un producto, al pulsar el botón de búsqueda,
  si se posee Internet, nos permitirá buscar dicho producto en MELI. Si posee referencia a dicho producto, traerá una lista de 50 artículos
  presentados en forma de lista, en caso contrario mostrara un mensaje indicando que no es posible. Cada item de dicha lista cuenta con :
	.Título de producto
	.Precio del producto
	.Imagen del producto
  Al tappear unos de los items, nos llevara a la "Product Page".

2)Product Page --> Esta es la vista que se llega a través del anterior tappeo de un producto de la lista en la vista "Searcher".
  En dicha vista se podrá visualizar:
	.Título de producto
	.Precio del producto
	.Una imagen del producto
	.Condición del producto
	.Cantidad en stock
	.Cantidad vendida
	.Indicación si el pago se puede realizar a través de "Mercado Pago"
	.Botón que lleva a la publicación original en la pagina de "Mercado Libre"
-Aplicación de Patrón MVP, se busco desacoplar lo mas posible la vista del modelo, y que ambos realicen la menor lógica posible. Dicha lógica se
  encuentra en las clases Presenter, la que ademas se encarga de "enlazar" la vista con el modelo.
-Test para clase ListArticleParse.
-Uso de librería Volley para llevar a cabo las peticiones a la API.
-Idioma Ingles y Español.
-Icono personalizado.

APRECIACIÓN PERSONAL
-Como objetivo principal del proyecto, se busco lograr cumplir con las consignas, dando una buena estructura al código.

FALTANTE
-Cantidad de test pobre o casi nula, debería hacerse muchos mas test, pero el esfuerzo fue puesto en aplicar nuevos conocimientos y sumar funcionalidades.
-Diagramas de clase, flujo y actividad.
-Comentarios en código.

POSIBLES FEATURES
-Agregar botones para poder realizar filtros en las búsquedas(como por precio máximo).
-Agregar "Paginas" para los artículos que se pueden traer desde "Mercado Libre", ya que actualmente es una sola pagina con 50 items.
-Mejorar la experiencia de usuario(tanto con manejo de errores como visuales para indicar que esta "cargando").
-Agregar en "Product Page" :
	.Mas de una imagen en caso de poseerlas.
	.Zoom a imágenes.
	.Datos del vendedor(como ubicación, y la categoría del mismo).
	.Preguntas al vendedor.
	.Si el envió es gratis.
