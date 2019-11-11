a tener en cuenta:
- crear los topics en forma de script con algun api conocido: frankz por ejemplo
- asignar las propiedades de cleanup.policy para que solo borre y
- min.cleanable.dirty.ratio para que compacte, tener en cuenta el heap y el tail
- hacer que los tombstones se borren despues de un dia
- hacer que el segment file sea de unos megas para ver como compacta cuando se llene
- crear las dlq en forma de script con algun api conocido: frankz por ejemplo
- asignar las particiones de forma adecuada.
- asignar los hilos para compactar/limpiar los topics de forma adecuada.
