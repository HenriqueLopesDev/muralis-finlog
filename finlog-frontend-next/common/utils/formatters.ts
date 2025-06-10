export function formatDateToBrazilianStandard(date: string): string {
  const [year, month, day] = date.split('-')
  const dateType = new Date(parseInt(year), parseInt(month) - 1, parseInt(day))
  return new Intl.DateTimeFormat('pt-BR').format(dateType)
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
