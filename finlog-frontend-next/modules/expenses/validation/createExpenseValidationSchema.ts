import z from 'zod'

export const createExpenseValidationSchema = z.object({
  description: z.string().trim().min(1, 'Descrição é obrigatória'),
  value: z.string().min(1, 'Valor é obrigatório'),
  expenseDate: z.string().min(1, 'Data é obrigatória'),
  paymentType: z.string().min(1, 'Tipo de pagamento é obrigatório'),
  category: z.string().trim().min(1, 'Categoria é obrigatória'),
  categoryDescription: z
    .string()
    .trim()
    .min(1, 'Descrição da categoria é obrigatória'),
  cep: z.string().min(8, 'CEP é obrigatório'),
  street: z.string().trim().min(1, 'Logradouro é obrigatório'),
  streetNumber: z.string().trim().min(1, 'Número é obrigatório'),
  neighborhood: z.string().trim().min(1, 'Bairro é obrigatório'),
  city: z.string().trim().min(1, 'Cidade é obrigatória'),
  state: z.string().min(1, 'Estado é obrigatório'),
  complement: z.string().optional(),
})

export type CreateExpenseFormData = z.infer<
  typeof createExpenseValidationSchema
>
