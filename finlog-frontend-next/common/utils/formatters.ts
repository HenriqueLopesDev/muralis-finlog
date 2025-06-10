export function formatDateToBrazilianStandard(date: string): string {
  return new Intl.DateTimeFormat('pt-BR').format(new Date(date))
}

export function formatToBrazilianCurrency(
  value: number | bigint | Intl.StringNumericLiteral,
): string {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(value)
}

export function removeNonNumericCharacters(value: string): string {
  return value.replace(/\D/g, '')
}

export function formatBrazilianZipCode(value: string) {
  if (!value) return ''
  value = value.replace(/\D/g, '')
  value = value.replace(/(\d{5})(\d)/, '$1-$2')
  return value
}
