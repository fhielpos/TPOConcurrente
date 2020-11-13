# TP 4 - Semaforos

## Ej 13
## Enunciado
    OBLIGATORIO - Una pequeña empresa (Los Pollos Hermanos) de 6 empleados dispone de
    un pequeño lugar donde los empleados pueden tomar algo rápido (por ejemplo 1 café y una
    porción de pollo frito). La pequeña confitería es atendida por un mozo y tiene espacio para 1
    empleado.
    El mozo se encarga de servir bebidas y el cocinero se encarga de la comida.
    Cuando un empleado desea servirse algo se acerca al lugar, si el espacio está libre se queda,
    e indica al mozo que está allí para que le sirvan. Elige lo que desea de una pequeña lista de
    opciones, y espera pacientemente que le sirvan. Cuando le han servido, le indican que
    puede empezar a comer. El empleado come y cuando termina deja la silla libre, agradece al
    mozo y vuelve a trabajar.
    Por su parte el mozo, cuando no hay empleados que atender, aprovecha a disfrutar de su
    hobbie de inventar nuevas versiones de pollo, hasta que llegue otro empleado.
    a. Escriba un programa que ejecute las funciones descritas utilizando los
    mecanismos de sincronización que considere necesarios. Debe existir un hilo para
    el mozo y un hilo para cada empleado. La solución debe estar libre de bloqueos.
    La solución debe maximizar la concurrencia de los hilos.