export interface IMapper<baseType, mappedType> {
  map(baseType: baseType): mappedType
}
