export interface Category {
  id: number
  name: string
  description: string
}

export type CategorySummary = Omit<Category, 'description'>
