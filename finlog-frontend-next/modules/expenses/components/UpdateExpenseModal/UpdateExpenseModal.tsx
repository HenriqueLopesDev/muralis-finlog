import * as React from 'react'
import { Divider } from '@mui/material'
import { BaseLabel } from '@/common/components/BaseLabel/BaseLabel'
import { BaseInput } from '@/common/components/BaseInput/BaseInput'
import { BaseSelect } from '@/common/components/BaseSelect/BaseSelect'
import { FormProvider, useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { FormValidationErrorMessage } from '@/common/components/FormValidationErrorMessage/FormValidationErrorMessage'
import {
  formatBrazilianZipCode,
  formatToBrazilianCurrency,
  removeNonNumericCharacters,
} from '@/common/utils/formatters'
import { useLazyGetPaymentTypesQuery } from '@/modules/paymentTypes/lib/redux/PaymentTypesApiSlice'
import { ActionAlert } from '@/common/utils/ActionAlert'
import {
  useLazyGetExpenseByIdQuery,
  useUpdateExpenseMutation,
} from '@/modules/expenses/lib/redux/ExpensesApiSlice'
import { CreateExpenseToCreateExpenseRequest } from '@/modules/expenses/mappers/CreateExpenseToCreateExpenseRequest'
import {
  CreateExpenseFormData,
  createExpenseValidationSchema,
} from '@/modules/expenses/validation/createExpenseValidationSchema'
import { useZipCode } from '../../hooks/useZipCode'
import { getMaxLocalDate } from '@/common/utils/getMaxLocalDate'
import { UpdateExpenseModalProps } from './UpdateExpenseModalProps'
import { BaseModal } from '@/common/components/BaseModal/BaseModal'

export function UpdateExpenseModal({
  updateExpenseModalState,
  setUpdateExpenseModalState,
}: UpdateExpenseModalProps) {
  const [fetchPaymentTypes, { data: paymentTypes }] =
    useLazyGetPaymentTypesQuery()

  const [fetchExpenseById, { data: foundExpense }] =
    useLazyGetExpenseByIdQuery()

  const formRef = React.useRef<HTMLFormElement>(null)
  const [updateQuotation] = useUpdateExpenseMutation()

  const hookFormMethods = useForm<CreateExpenseFormData>({
    resolver: zodResolver(createExpenseValidationSchema),
    defaultValues: {
      description: '',
      value: '',
      expenseDate: '',
      paymentType: '',
      category: '',
      categoryDescription: '',
      cep: '',
      street: '',
      streetNumber: '',
      neighborhood: '',
      city: '',
      state: '',
    },
  })

  const {
    handleSubmit,
    register,
    formState: { errors },
    setValue,
    reset,
  } = hookFormMethods

  const populateFormFields = React.useCallback(() => {
    if (!foundExpense) return
    setValue('description', foundExpense.description)
    setValue('value', formatToBrazilianCurrency(foundExpense.value))
    setValue('expenseDate', foundExpense.date)
    setValue('paymentType', String(foundExpense.paymentType.id))
    setValue('category', foundExpense.category.name)
    setValue('categoryDescription', foundExpense.category.description)
    setValue('cep', formatBrazilianZipCode(foundExpense.address.zipCode))
    setValue('street', foundExpense.address.street)
    setValue('streetNumber', foundExpense.address.streetNumber)
    setValue('neighborhood', foundExpense.address.neighborhood)
    setValue('city', foundExpense.address.city)
    setValue('state', foundExpense.address.state)
    setValue('complement', foundExpense.address.complement || '')
  }, [foundExpense, setValue])

  const {
    addressLockedFields,
    setAllAddressFieldsLocked,
    handleZipCodeChange,
  } = useZipCode(setValue)

  React.useEffect(() => {
    if (updateExpenseModalState.open && updateExpenseModalState.expenseId) {
      fetchPaymentTypes()
      fetchExpenseById(updateExpenseModalState.expenseId)
        .unwrap()
        .then(() => populateFormFields())
    }
  }, [
    fetchExpenseById,
    fetchPaymentTypes,
    populateFormFields,
    updateExpenseModalState.expenseId,
    updateExpenseModalState.open,
  ])

  const handleCloseUpdateExpenseMOdal = () => {
    setUpdateExpenseModalState({ expenseId: undefined, open: false })
    setAllAddressFieldsLocked(false)
    reset()
  }

  const onSubmit = async (data: CreateExpenseFormData) => {
    const { error } = await updateQuotation({
      id: updateExpenseModalState.expenseId!,
      data: new CreateExpenseToCreateExpenseRequest().map(data),
    })

    if (error) {
      return ActionAlert.show({
        icon: 'error',
        title: 'Erro ao atualizar a despesa, tente novamente mais tarde.',
      })
    }

    ActionAlert.show({
      icon: 'success',
      title: 'Despesa atualizada com sucesso!',
    })

    handleCloseUpdateExpenseMOdal()
  }

  const formatCurrentyInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const numericValue =
      Number(removeNonNumericCharacters(event.target.value)) / 100
    event.target.value = formatToBrazilianCurrency(numericValue)
  }

  return (
    <BaseModal
      open={updateExpenseModalState.open}
      onClose={handleCloseUpdateExpenseMOdal}
      maxWidth="md"
      title="Atualizar despesa"
    >
      <FormProvider {...hookFormMethods}>
        <form onSubmit={handleSubmit(onSubmit)} ref={formRef}>
          <div className="grid grid-cols-4 gap-4">
            <div className="flex flex-col gap-1 col-span-3 max-[768px]:col-span-4">
              <BaseLabel
                title="Descrição"
                requiredIndicator
                htmlFor="description"
              />
              <BaseInput
                id="description"
                placeholder="Insira a descrição da despesa"
                {...register('description')}
              />
              {errors.description && (
                <FormValidationErrorMessage>
                  {errors.description.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-1 max-[768px]:col-span-2 max-[655px]:col-span-4">
              <BaseLabel htmlFor="value" title="Valor" requiredIndicator />
              <BaseInput
                id="value"
                placeholder="Insira o valor da despesa"
                className="col-span-1"
                {...register('value')}
                onChange={formatCurrentyInput}
              />
              {errors.value && (
                <FormValidationErrorMessage>
                  {errors.value.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-2 max-[768px]:col-span-2 max-[655px]:col-span-4">
              <BaseLabel htmlFor="expenseDate" title="Data" requiredIndicator />
              <BaseInput
                id="expenseDate"
                type="date"
                placeholder="Insira a data da despesa"
                className="w-full"
                max={getMaxLocalDate()}
                {...register('expenseDate')}
              />
              {errors.expenseDate && (
                <FormValidationErrorMessage>
                  {errors.expenseDate.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-2 max-[655px]:col-span-4">
              <BaseLabel
                htmlFor="paymentType"
                title="Tipo de pagamento"
                requiredIndicator
              />
              <BaseSelect id="paymentType" {...register('paymentType')}>
                <option value={''} hidden>
                  Selecione o tipo de pagamento
                </option>
                {paymentTypes?.map((paymentType) => (
                  <option key={paymentType.id} value={paymentType.id}>
                    {paymentType.type}
                  </option>
                ))}
              </BaseSelect>
              {errors.paymentType && (
                <FormValidationErrorMessage>
                  {errors.paymentType.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-2 max-[655px]:col-span-4">
              <BaseLabel
                htmlFor="category"
                title="Categoria"
                requiredIndicator
              />
              <BaseInput
                id="category"
                placeholder="Insira a categoria da despesa"
                {...register('category')}
              />
              {errors.category && (
                <FormValidationErrorMessage>
                  {errors.category.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-2 max-[655px]:col-span-4">
              <BaseLabel
                htmlFor="categoryDescription"
                title="Descrição da categoria"
                requiredIndicator
              />
              <BaseInput
                id="categoryDescription"
                placeholder="Insira a descrição da categoria da despesa"
                {...register('categoryDescription')}
              />
              {errors.categoryDescription && (
                <FormValidationErrorMessage>
                  {errors.categoryDescription.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-1 max-[768px]:col-span-2 max-[655px]:col-span-4">
              <BaseLabel htmlFor="cep" title="CEP" requiredIndicator />
              <BaseInput
                id="cep"
                placeholder="Insira o CEP da despesa"
                {...register('cep')}
                onChange={handleZipCodeChange}
                maxLength={9}
              />
              {errors.cep && (
                <FormValidationErrorMessage>
                  {errors.cep.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-2 max-[655px]:col-span-4">
              <BaseLabel
                htmlFor="street"
                title="Logradouro"
                requiredIndicator
              />
              <BaseInput
                id="street"
                placeholder="Insira o logradouro da despesa"
                {...register('street')}
                readOnly={addressLockedFields.street}
              />
              {errors.street && (
                <FormValidationErrorMessage>
                  {errors.street.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-1 max-[768px]:col-span-2 max-[655px]:col-span-4">
              <BaseLabel
                htmlFor="streetNumber"
                title="Número"
                requiredIndicator
              />
              <BaseInput
                id="streetNumber"
                placeholder="Insira o número do endereço"
                {...register('streetNumber')}
              />
              {errors.streetNumber && (
                <FormValidationErrorMessage>
                  {errors.streetNumber.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-2 max-[655px]:col-span-4">
              <BaseLabel
                htmlFor="neighborhood"
                title="Bairro"
                requiredIndicator
              />
              <BaseInput
                id="neighborhood"
                placeholder="Insira o bairro do endereço"
                {...register('neighborhood')}
                readOnly={addressLockedFields.neighborhood}
              />
              {errors.neighborhood && (
                <FormValidationErrorMessage>
                  {errors.neighborhood.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-2 max-[655px]:col-span-4">
              <BaseLabel htmlFor="city" title="Cidade" requiredIndicator />
              <BaseInput
                id="city"
                placeholder="Insira a cidade do endereço"
                {...register('city')}
                readOnly={addressLockedFields.city}
              />
              {errors.city && (
                <FormValidationErrorMessage>
                  {errors.city.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-1 max-[992px]:col-span-2 max-[655px]:col-span-4">
              <BaseLabel htmlFor="state" title="Estado" requiredIndicator />
              {addressLockedFields.city ? (
                <BaseInput
                  id="state"
                  readOnly
                  {...register('state')}
                  placeholder="Insira o estado"
                />
              ) : (
                <BaseSelect id="state" {...register('state')}>
                  <option value="" hidden>
                    Selecione o estado
                  </option>
                  <option value="SP">SP</option>
                  <option value="RJ">RJ</option>
                </BaseSelect>
              )}
              {errors.state && (
                <FormValidationErrorMessage>
                  {errors.state.message}
                </FormValidationErrorMessage>
              )}
            </div>
            <div className="flex flex-col gap-1 col-span-3 max-[992px]:col-span-2 max-[655px]:col-span-4">
              <BaseLabel htmlFor="complement" title="Complemento" />
              <BaseInput
                id="complement"
                placeholder="Insira o complemento do endereço"
                {...register('complement')}
              />
            </div>
          </div>
          <p className="font-poppins text-[14px] my-3">
            Campos marcados com * são obrigatórios.
          </p>
          <Divider />
          <div className="flex justify-between mt-3">
            <button
              className="h-10 border border-[var(--color-01)] hover:cursor-pointer flex items-center justify-center gap-1 rounded-[10px] bg-transparent px-3 py-2 font-poppins font-semibold text-[var(--color-01)] transition-colors duration-200 hover:bg-[var(--color-02)] hover:text-white"
              type="button"
              onClick={handleCloseUpdateExpenseMOdal}
            >
              Cancelar
            </button>
            <button
              className="hover:cursor-pointer flex items-center justify-center gap-1 rounded-[10px] bg-[var(--color-01)] px-3 py-2 font-poppins font-semibold text-white transition-colors duration-200 hover:bg-[var(--color-02)]"
              type="submit"
            >
              Atualizar
            </button>
          </div>
        </form>
      </FormProvider>
    </BaseModal>
  )
}
